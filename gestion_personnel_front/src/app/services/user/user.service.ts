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
import { HoursPerWeek } from '../../models/hoursPerWeek/hours-per-week';
import { Month } from '../../models/month/month';
import { Year } from '../../models/year/year';

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

  getAllHoursPerWeek(): Observable<HoursPerWeek[]> {
    return this.http.get<HoursPerWeek[]>(API_URL_USER + 'getAllHoursPerWeek');
  }

  createRecording(recording: Recording): Observable<void> {
    return this.http.post<void>(API_URL_USER + 'createRecording', recording);
  }

  getEmployeeById(employeeId: number): Observable<Employee> {
    return this.http.get<Employee>(API_URL_USER + 'getEmployee/' + employeeId);
  }

  getAllMonth(): Observable<Month[]> {
    return this.http.get<Month[]>(API_URL_USER + 'getAllMonth');
  }

  getAllYear(): Observable<Year[]> {
    return this.http.get<Year[]>(API_URL_USER + 'getAllYear');
  }

  getAllEmployeeRecordings(employeeId: number, year: number, monthId: number): Observable<Recording[]>{
    return this.http.get<Recording[]>(API_URL_USER + 'getAllEmployeeRecording/' + employeeId + '/' + year + '/' + monthId);
  }

  getNbSpecialDay(employeeId: number, yearId: number, monthId: number): Observable<Map<string,number[]>> {
    return this.http.get<Map<string,number[]>>(API_URL_USER + 'getEmployeeNbHolidaysRttsIllnesses/' + employeeId + '/' + yearId + '/' + monthId);
  }

  addDaysToEmployeeSpecialDay(employeeId: number, dayname: string, nbDay: number): Observable<void> {
    return this.http.get<void>(API_URL_USER + 'addDaysToEmployeeSpecialDay/' + employeeId + '/' + dayname + '/' + nbDay);
  }

  getEmployeeWorkingHours(employeeId: number, yearId: number, monthId: number): Observable<Map<string,string[]>> {
    return this.http.get<Map<string,string[]>>(API_URL_USER + 'getEmployeeWorkingHours/' + employeeId + '/' + yearId + '/' + monthId);
  }
}
