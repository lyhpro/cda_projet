import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../../models/employee/employee/employee';
import { Observable, Subscription } from 'rxjs';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-special-day',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './special-day.component.html',
  styleUrl: './special-day.component.css'
})
export class SpecialDayComponent implements OnInit {

  dayname!: string;
  employeeId!: number;

  form!: FormGroup;

  employee!: Employee;
  employee$: Observable<Employee> | undefined;
  subscriptionEmployee!: Subscription;

  constructor(
    private userService: UserService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.dayname = this.activatedRoute.snapshot.params['dayname'];
    this.employeeId = this.activatedRoute.snapshot.params['employeeId'];
    this.form = this.formBuilder.group(
      {
        employeeId: [this.employeeId],
        dayname: [this.dayname],
        nbDay: [0]
      }
    )

    this.initEmployeeById(this.employeeId);
    
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

  onSubmit() {
    this.userService.addDaysToEmployeeSpecialDay(this.form.value.employeeId, this.form.value.dayname, this.form.value.nbDay).subscribe();
    alert("Jours ajoutés");
    this.router.navigate(['home/employee/display',this.employeeId]);     
  }

}
