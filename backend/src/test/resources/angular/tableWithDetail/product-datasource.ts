import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { catchError, map } from 'rxjs/operators';
import { Observable, merge, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

export interface ProductItem {
  id: number;
  symbol1: string;
  symbol2: string;
  name: string;
  description: string;
}

/**
 * Data source for the Product view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class ProductDataSource extends DataSource<ProductItem> {
  apiUrl: string = 'http://localhost:8080/products';
  data: ProductItem[] = [];
  paginator: MatPaginator | undefined;
  sort: MatSort | undefined;

  constructor(private http: HttpClient) {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<OrganizationItem[]> {
    if (this.paginator && this.sort) {
      // Combine everything that affects the rendered data into one update
      // stream for the data-table to consume.
      return merge(this.paginator.page, this.sort.sortChange,
        this.http.get<OrganizationItem[]>('http://localhost:8080/organizations/').pipe(map(data => this.data = data)))
        .pipe(map(() => {
          return this.getPagedData(this.getSortedData([...this.data]));
        }));
    } else {
      throw Error('Please set the paginator and sort on the data source before connecting.');
    }
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect(): void { }

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: OrganizationItem[]): OrganizationItem[] {
    if (this.paginator) {
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
      return data.splice(startIndex, this.paginator.pageSize);
    } else {
      return data;
    }
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: OrganizationItem[]): OrganizationItem[] {
    if (!this.sort || !this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort?.direction === 'asc';
      switch (this.sort?.active) {
        case 'name': return compare(a.shortName, b.shortName, isAsc);
        case 'id': return compare(+a.id, +b.id, isAsc);
        default: return 0;
      }
    });
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Return an observable with a user-facing error message.
    return throwError(
      'Something bad happened; please try again later.');
  }

  public getItem(id: number): Observable<OrganizationItem> {
    return this.http.get<OrganizationItem>(this.apiUrl + '/' + id);
  }

  public addItem(newItem) {
    console.log(newItem);
    return this.http.post<OrganizationItem>(this.apiUrl, newItem)
      .pipe(
        catchError(this.handleError),
        map((savedItem) => this.data.push(savedItem))
      );
  }

  public updateItem(updatedItem): Observable<void> {
    console.log(updatedItem);
    return this.http.put<OrganizationItem>(this.apiUrl + '/' + updatedItem.id, updatedItem)
      .pipe(
        catchError(this.handleError),
        map((savedItem) => {
          this.data = this.data.filter((value, key) => {
            if (value.id == savedItem.id) {
              value.symbol = savedItem.symbol;
              value.shortName = savedItem.shortName;
            }
            return true;
          })
        }
        )
      );
  }

  deleteItem(deletedItem): Observable<void> {
    return this.http.delete(this.apiUrl + '/' + deletedItem.id)
      .pipe(
        catchError(this.handleError),
        map(() => {
          this.data = this.data.filter((value, key) => {
            return value.id != deletedItem.id;
          })
        })
      );
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a: string | number, b: string | number, isAsc: boolean): number {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}