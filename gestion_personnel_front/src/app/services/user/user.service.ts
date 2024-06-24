import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContractType } from '../../models/contractType/contract-type';
import { Role } from '../../models/role/role';
import { Department } from '../../models/department/department';
import { EmployeeForm } from '../../models/employee/employee-form/employee-form';
import { Employee } from '../../models/employee/employee/employee';
import { DayType } from '../../models/dayType/day-type';
import { Recording } from '../../models/recording/recording';

const API_URL_USER = 'http://localhost:8080/gestionnaire-personnel/user/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllContractType(): Observable<ContractType[]> {
    return this.http.get<Role[]>(API_URL_USER + 'getAllContractType');
  }

  getAllDepartment(): Observable<Department[]> {
    return this.http.get<Department[]>(API_URL_USER + 'getAllDepartment');
  }

  createEmploye(employeForm: EmployeeForm): Observable<Employee> {
    return this.http.post<Employee>(API_URL_USER + 'createEmployee',employeForm);
  }

  getAllEmployee(): Observable<Employee[]> {
    return this.http.get<Employee[]>(API_URL_USER + 'getAllEmployee');
  }

  getAllDayType(): Observable<DayType[]> {
    return this.http.get<DayType[]>(API_URL_USER + 'getAllDayType');
  }

  createRecording(recording: Recording): Observable<Recording> {
    return this.http.post<Recording>(API_URL_USER + 'createRecording', recording);
  }
}
