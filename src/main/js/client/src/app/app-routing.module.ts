import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CampaignListComponent } from './campaign-list/campaign-list.component';
import { CampaignEditComponent } from './campaign-edit/campaign-edit.component';

const routes: Routes = [
  { path: '', redirectTo: '/campaign-list', pathMatch: 'full' },
  {
    path: 'campaign-list',
    component: CampaignListComponent
  },
  {
    path: 'campaign-add',
    component: CampaignEditComponent
  },
  {
    path: 'campaign-edit/:id',
    component: CampaignEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
