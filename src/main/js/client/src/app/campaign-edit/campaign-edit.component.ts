import { Component, OnDestroy, OnInit } from '@angular/core';
import {Observable, Subscription} from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { CampaignService } from '../shared/campaign/campaign.service';
import {FormControl, NgForm} from '@angular/forms';
import {map, startWith} from "rxjs/operators";

interface City {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-campaign-edit',
  templateUrl: './campaign-edit.component.html',
  styleUrls: ['./campaign-edit.component.css']
})
export class CampaignEditComponent implements OnInit, OnDestroy {
  campaign: any = {};
  budget: number;
  sub: Subscription;

  cities: City[] = [
    {value: 'Warszawa', viewValue: 'Warszawa'},
    {value: 'Kraków', viewValue: 'Kraków'},
    {value: 'Gdańsk', viewValue: 'Gdańsk'},
    {value: 'Katowice', viewValue: 'Katowice'}
  ];

  myControl = new FormControl();
  options: string[] = ['Health', 'Fashion', 'Food', 'Technology', 'Politics'];
  filteredOptions: Observable<string[]>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private campaignService: CampaignService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.campaignService.get(id).subscribe((campaign: any) => {
          if (campaign) {
            this.campaign = campaign;
            this.campaign.href = campaign._links.self.href;
          } else {
            console.log(`Campaign with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
    this.campaignService.getBudget().subscribe(budget => {
      this.budget = budget;
    })
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/campaign-list']);
  }

  save(form: NgForm) {
    this.campaignService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.campaignService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
}
