import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../../../models/employee/employee/employee';
import { Observable, Subscription, map } from 'rxjs';

@Component({
  selector: 'app-employee-display',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee-display.component.html',
  styleUrl: './employee-display.component.css'
})
export class EmployeeDisplayComponent implements OnInit {

  months!: string[];
  form!: FormGroup;
  employeeId!: number;

  monthSelected!: string;
  yearSelected!: number;

  employee!: Employee;
  employee$: Observable<Employee> | undefined;
  subscriptionEmployee!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.months = ['Janvier','Fevrier','Mars','Avril','Mai','Juin','Juillet','Aout','Septembre','Octobre','Novembre','Decembre'];
    this.employeeId = this.activatedRoute.snapshot.params['id'];
    this.form = this.formBuilder.group(
      {
        year: new FormControl(''),
        month: new FormControl(''),
      }
    )
    this.initEmployeeById(this.employeeId);
  }

  onSubmit() {
    this.loadFormWithData();
    console.log(this.employee.id);
    console.log(this.yearSelected);
    console.log(this.monthSelected);
    
  }

  initEmployeeById(employeeId: number) {
    this.employee$ = this.userService.getEmployeeById(employeeId);
    this.subscriptionEmployee = this.employee$.subscribe(
      {
        next: resp => {
          this.employee = new Employee(resp.id,resp.secondname,resp.firstname,resp.placeOfBirth,resp.dateOfBirth,resp.enable,resp.dateOfCreation,resp.contactDetailId,resp.professionalDetailId);
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Employe chargé.");
        }
      }
    )
  }

  loadFormWithData() {
    this.yearSelected = this.form.value.year;
    this.monthSelected = this.form.value.month;
  }

}
