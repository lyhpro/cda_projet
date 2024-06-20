import { CommonModule, Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee/employee/employee';
import { Observable, Subscription } from 'rxjs';
import { UserService } from '../../services/user/user.service';
import { DayType } from '../../models/dayType/day-type';
import { Recording } from '../../models/recording/recording';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-recording',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './recording.component.html',
  styleUrl: './recording.component.css'
})
export class RecordingComponent implements OnInit {

  recording!: Recording;
  recordingForm!: FormGroup;

  initTime!: Time;

  employees!: Employee[];
  employees$: Observable<Employee[]> | undefined;
  subscriptionEmployees!: Subscription;

  dayTypes!: DayType[];
  dayTypes$: Observable<DayType[]> | undefined;
  subscriptiondayTypes!: Subscription;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    // this.initTime.hours = 0;
    // this.initTime.minutes = 0;
    this.recording = new Recording(0,new Date(),this.initTime, this.initTime, this.initTime,this.initTime,this.initTime,this.initTime,this.initTime,0,0);
    this.recordingForm = this.formBuilder.group(
      {
        id: new FormControl(''),
        date: new FormControl(''),
        hourStart: new FormControl(''),
        hourStop: new FormControl(''), 
        breakStart: new FormControl(''),
        breakStop: new FormControl(''),
        totalHours: new FormControl(''), 
        extraHours: new FormControl(''), 
        dueHours: new FormControl(''), 
        employeeId: new FormControl(''),
        dayTypeId: new FormControl('')
      }
    )
    this.initEmployees();
    this.initDayTypes();
  }

  onSubmit() {

  }

  initEmployees() {
    this.employees$ = this.userService.getAllEmployee();
    this.subscriptionEmployees = this.employees$.subscribe(
      {
        next: resp => {
          this.employees = resp;
          console.log(this.employees)
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des employes charge.");
        }
      }
    )
  }

  initDayTypes() {
    this.dayTypes$ = this.userService.getAllDayType();
    this.subscriptiondayTypes = this.dayTypes$.subscribe(
      {
        next: resp => {
          this.dayTypes = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des types de journées chargé.");
        }
      }
    )
  }

}
