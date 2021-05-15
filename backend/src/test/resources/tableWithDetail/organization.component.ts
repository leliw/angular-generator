import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { OrganizationDataSource, OrganizationItem } from './organization-datasource';
import { HttpClient } from '@angular/common/http';
import { DialogBoxComponent } from '../dialog-box/dialog-box.component';

@Component({
  selector: 'app-organization',
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<OrganizationItem>;
  dataSource: OrganizationDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'symbol', 'nip', 'shortName', 'postalCode', 'city', 'actions'];

  constructor(private http: HttpClient, public dialog: MatDialog ) {
    this.dataSource = new OrganizationDataSource(http);
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  openDialog(action: string, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      data:obj
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if (result.event == "Delete")
        this.dataSource.deleteItem(result.data).subscribe(
          () => this.dataSource.paginator.page.emit()
        )
    });
  }
}
