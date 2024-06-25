import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { EmployeeForm } from '../../../models/employee/employee-form/employee-form';
import { UserService } from '../../../services/user/user.service';
import { Department } from '../../../models/department/department';
import { ContractType } from '../../../models/contractType/contract-type';
import { Observable, Subscription, map } from 'rxjs';
import { Router } from '@angular/router';
import { Employee } from '../../../models/employee/employee/employee';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {

  employees!: Employee[];
  employees$: Observable<Employee[]> | undefined;
  subscriptionEmployees!: Subscription;

  constructor(
    private router: Router,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.initListEmployee();
  }

  goToAddEmployee() {
    this.router.navigateByUrl('home/employee/add');
  }

  goToDisplayEmployee(employeeId: number) {
    this.router.navigate(['/home/employee/display', employeeId]);
  }

  initListEmployee() {
    this.employees$ = this.userService.getAllEmployee().pipe(
      map(
        employees => {
          return employees.map(
            employee => {
              const newEmployee = new Employee(employee.id,employee.secondname,employee.firstname,employee.placeOfBirth,employee.dateOfBirth,employee.enable,employee.dateOfCreation,employee.contactDetailId,employee.professionalDetailId);
              return newEmployee;
            }
          )
        }
      )
    );
    this.subscriptionEmployees = this.employees$.subscribe(
      {
        next: resp => {
          this.employees = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des employés chargé.");
        }
      }
    )
  }

}
