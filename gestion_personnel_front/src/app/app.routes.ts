import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    {path: 'auth', component: LandingPageComponent},
    {path: 'home', component: HomeComponent},

    {path: '', redirectTo: 'auth', pathMatch: 'full'},
    {path: '**', redirectTo: 'home', pathMatch: 'full'}
];
