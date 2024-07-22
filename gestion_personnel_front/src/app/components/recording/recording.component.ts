import { CommonModule, Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee/employee/employee';
import { Observable, Subscription, map } from 'rxjs';
import { UserService } from '../../services/user/user.service';
import { DayType } from '../../models/dayType/day-type';
import { Recording } from '../../models/recording/recording';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PopupService } from '../../services/popup/popup.service';

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
    private formBuilder: FormBuilder,
    private popupService: PopupService
  ) {}

  ngOnInit(): void {
    this.title = '';
    this.recording = new Recording(0,"","","","","","","","","","",0,0);
    this.recordingForm = this.formBuilder.group(
      {
        id: new FormControl(0, Validators.required),
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
        employeeId: new FormControl(0, Validators.required),
        dayTypeId: new FormControl(0, Validators.required)
      }
    )
    this.initEmployees();
    this.initDayTypes();

    this.reloadRecordingForm();
  }

  onSubmit() {
    this.initRecordingFormWithForm();
    this.userService.createRecording(this.recording).subscribe();
    this.popupService.openPopup("Enregistrement effectué",2000,true);
  }

  initEmployees() {
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
          console.log("Liste des employes charge.");
        }
      }
    )
  }

  initDayTypes() {
    this.dayTypes$ = this.userService.getAllDayType().pipe(
      map(
        dayTypes => {
          return dayTypes.map(
            dayType => {
              const newDayType = new DayType(dayType.id,dayType.name,dayType.enable);
              return newDayType;
            }
          )
        }
      )
    );
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

    this.recordingForm.get('dayTypeId')?.valueChanges.subscribe(
      dayTypeId => {

        const dateControl = this.recordingForm.get('date');
        dateControl?.setValue('');
        dateControl?.clearValidators();
    
        const dateStartControl = this.recordingForm.get('dateStart');
        dateStartControl?.setValue('');
        dateStartControl?.clearValidators();
    
        const dateStopControl = this.recordingForm.get('dateStop');
        dateStopControl?.setValue('');
        dateStopControl?.clearValidators();
    
        const hourStartControl = this.recordingForm.get('hourStart');
        hourStartControl?.setValue('');
        hourStartControl?.clearValidators();
        
        const hourStopControl = this.recordingForm.get('hourStop');
        hourStopControl?.setValue('');
        hourStopControl?.clearValidators();
    
        const breakStartControl = this.recordingForm.get('breakStart');
        breakStartControl?.setValue('');
        breakStartControl?.clearValidators();
    
        const breakStopControl = this.recordingForm.get('breakStop');
        breakStopControl?.setValue('');
        breakStopControl?.clearValidators();
        
        if(dayTypeId == 1) {

          dateControl?.setValidators([Validators.required]);
          hourStartControl?.setValidators([Validators.required]);
          hourStopControl?.setValidators([Validators.required]);
          breakStartControl?.setValidators([Validators.required]);
          breakStopControl?.setValidators([Validators.required]);

        } else {
          dateStartControl?.setValidators([Validators.required]);
          dateStopControl?.setValidators([Validators.required]);
        }

        dateControl?.updateValueAndValidity();
        dateStartControl?.updateValueAndValidity();
        dateStopControl?.updateValueAndValidity();
        hourStartControl?.updateValueAndValidity();
        hourStopControl?.updateValueAndValidity();
        breakStartControl?.updateValueAndValidity();
        breakStopControl?.updateValueAndValidity();

      }
    )
  
  }

  isTravailDayType(dayTypeId: number): boolean {
    if(dayTypeId == 1) {
      return false;
    }
    return true;
  }

  resetDateForm() {
    console.log("ohe");
    
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
 
}
