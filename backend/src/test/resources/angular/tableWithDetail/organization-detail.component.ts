import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { OrganizationDataSource } from './organization-datasource';
import { Location } from '@angular/common';

@Component({
  selector: 'app-organization-detail',
  templateUrl: './organization-detail.component.html',
  styleUrls: ['./organization-detail.component.css']
})
export class OrganizationDetailComponent implements OnInit {

  addressForm = this.fb.group({
    id: null,
    symbol: [null, Validators.required],
    nip: [null, Validators.compose([
      Validators.required, Validators.minLength(10), Validators.maxLength(13)])
    ],
    pesel: null,
    shortName: [null, Validators.required],
    name1: null,
    name2: null,
    street: null,
    houseNo: null,
    flatNo: null,
    postalCode: null,
    city: null
  });

  hasUnitNumber = false;

  private dataSource: OrganizationDataSource;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private location: Location,
    http: HttpClient
  ) {
    this.dataSource = new OrganizationDataSource(http);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      if (params.id != '_new')
        this.dataSource.getItem(params.id).subscribe({
          next: (item) => { this.addressForm.patchValue(item) }
        });
    });
  }

  onSubmit(): void {
    if (this.route.snapshot.paramMap.get('id') == '_new')
      this.dataSource.addItem(this.addressForm.value).subscribe(
        data => { console.log('Success ', data), this.location.back(); },
        error => console.error('Opps ', error)
      );
    else
      this.dataSource.updateItem(this.addressForm.value).subscribe(
        data => { console.log('Success ', data), this.location.back(); },
        error => console.error('Opps ', error)
      );
  }

  goBack(): void {
    this.location.back();
  }
}
