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

export const routes: Routes = [
    {path: 'auth', component: LandingPageComponent},
    {path: 'home', component: HomeComponent},
    {path: 'home/user/add', component: UserAddComponent},
    {path: 'home/user/list', component: UserListComponent},
    {path: 'home/employee/display/:id', component: EmployeeDisplayComponent},
    {path: 'home/employee/list', component: EmployeeListComponent},
    {path: 'home/employee/add', component: EmployeeAddComponent},
    {path: 'home/employee/add-hours', component: RecordingComponent},
    {path: 'home/employee/add-special-day/:employeeId/:dayname', component: SpecialDayComponent},

    {path: '', redirectTo: 'auth', pathMatch: 'full'},
    {path: '**', redirectTo: 'home', pathMatch: 'full'}
];
