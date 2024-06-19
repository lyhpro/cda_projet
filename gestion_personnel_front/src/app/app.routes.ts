import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HomeComponent } from './components/home/home.component';
import { UserAddComponent } from './components/user/user-add/user-add.component';
import { UserListComponent } from './components/user/user-list/user-list.component';

export const routes: Routes = [
    {path: 'auth', component: LandingPageComponent},
    {path: 'home', component: HomeComponent},
    {path: 'home/user/add', component: UserAddComponent},
    {path: 'home/user/list', component: UserListComponent},

    {path: '', redirectTo: 'auth', pathMatch: 'full'},
    {path: '**', redirectTo: 'home', pathMatch: 'full'}
];
