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

  title!: string;

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
    this.recording = new Recording(0,new Date(),new Date(), new Date(), this.initTime, this.initTime, this.initTime,this.initTime,this.initTime,this.initTime,this.initTime,0,0);
    this.recordingForm = this.formBuilder.group(
      {
        id: new FormControl(''),
        date: new FormControl(''),
        dateStart: new FormControl(''),
        dateStop: new FormControl(''),
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
    this.initRecordingFormWithForm();
    this.userService.createRecording(this.recording).subscribe();
    alert("Enregistrement effectué.");
    location.reload();
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

  initRecordingFormWithForm() {
    this.recording.date = this.recordingForm.value.date;
    this.recording.dateStart = this.recordingForm.value.dateStart;
    this.recording.dateStop = this.recordingForm.value.dateStop;
    this.recording.hourStart = this.recordingForm.value.hourStart;
    this.recording.hourStop = this.recordingForm.value.hourStop; 
    this.recording.breakStart = this.recordingForm.value.breakStart;
    this.recording.breakStop = this.recordingForm.value.breakStop;
    this.recording.totalHours = this.recordingForm.value.totalHours; 
    this.recording.extraHours = this.recordingForm.value.extraHours; 
    this.recording.dueHours = this.recordingForm.value.dueHours; 
    this.recording.employeeId = this.recordingForm.value.employeeId;
    this.recording.dayTypeId = this.recordingForm.value.dayTypeId;
  }

  reloadRecordingForm() {    
    this.recordingForm.patchValue(
      {
        date: '',
        dateStart: '',
        dateStop: '',
        hourStart: '',
        hourStop: '',
        breakStart: '',
        breakStop: ''
      }
    );
  }

  displayDayType(dayTypeId: number) {
    this.reloadRecordingForm();
    if(dayTypeId == 1) {
      this.title = "TRAVAIL";
    } else if(dayTypeId == 2) {
      this.title = "VACANCE";
    } else if(dayTypeId == 3) {
      this.title = "RTT";
    } else if(dayTypeId == 4) {
      this.title = "MALADIE";
    }
  }

  isTravailDayType(dayTypeId: number): boolean {
    if(dayTypeId == 1) {
      return false;
    }
    return true;
  }
 
}