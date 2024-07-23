import { Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HomeComponent } from './components/home/home.component';
import { UserAddComponent } from './components/user/user-add/user-add.component';
import { UserListComponent } from './components/user/user-list/user-list.component';
import { EmployeeAddComponent } from './components/employee/employee-add/employee-add.component';
import { EmployeeListComponent } from './components/employee/employee-list/employee-list.component';
import { RecordingComponent } from './components/recording/recording.component';
import { EmployeeDisplayComponent } from './components/employee/employee-display/employee-display.component';
import { SpecialDayComponent } from './components/special-day/special-day.component';
import { authenticationGuard } from './guards/authentication/authentication.guard';
import { userGuard } from './guards/user/user.guard';

export const routes: Routes = [
    {path: 'auth', component: LandingPageComponent, canDeactivate: [authenticationGuard]},
    {path: 'home', component: HomeComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['ADMIN','USER']}},
    {path: 'home/user/add', component: UserAddComponent,  canActivate: [authenticationGuard, userGuard], data: {roles: ['ADMIN']}},
    {path: 'home/user/list', component: UserListComponent,  canActivate: [authenticationGuard, userGuard], data: {roles: ['ADMIN']}},
    {path: 'home/employee/display/:id', component: EmployeeDisplayComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['USER']}}, 
    {path: 'home/employee/list', component: EmployeeListComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['USER']}},
    {path: 'home/employee/add', component: EmployeeAddComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['USER']}},
    {path: 'home/employee/add-hours', component: RecordingComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['USER']}},
    {path: 'home/employee/add-special-day/:employeeId/:dayname', component: SpecialDayComponent, canActivate: [authenticationGuard, userGuard], data: {roles: ['USER']}},

    {path: '', redirectTo: 'auth', pathMatch: 'full'},
    {path: '**', redirectTo: 'home', pathMatch: 'full'}
];
