import { CommonModule, Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Employee } from '../../../models/employee/employee/employee';
import { Observable, Subscription, map } from 'rxjs';
import { Month } from '../../../models/month/month';
import { Recording } from '../../../models/recording/recording';
import { DayType } from '../../../models/dayType/day-type';
import { HoursFormatPipe } from '../../../pipes/hours/hours-format.pipe';
import { DurationsFormatPipe } from '../../../pipes/durations/durations-format.pipe';
import { Year } from '../../../models/year/year';

@Component({
  selector: 'app-employee-display',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HoursFormatPipe, DurationsFormatPipe],
  templateUrl: './employee-display.component.html',
  styleUrl: './employee-display.component.css'
})
export class EmployeeDisplayComponent implements OnInit {

  form!: FormGroup;
  employeeId!: number;

  monthSelected!: number;
  yearSelected!: number;

  years!: Year[];
  years$: Observable<Year[]> | undefined;
  subscriptionYears!: Subscription;

  months!: Month[];
  months$: Observable<Month[]> | undefined;
  subscriptionMonths!: Subscription;

  employee!: Employee;
  employee$: Observable<Employee> | undefined;
  subscriptionEmployee!: Subscription;

  recordings!: Recording[];
  recordings$: Observable<Recording[]> | undefined;
  subscriptionRecordings!: Subscription;

  dayTypes!: DayType[];
  dayTypes$: Observable<DayType[]> | undefined;
  subscriptiondayTypes!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.recordings = [];
    this.monthSelected = 0;
    this.yearSelected = 0;
    this.employeeId = this.activatedRoute.snapshot.params['id'];
    this.form = this.formBuilder.group(
      {
        year: new FormControl(0),
        month: new FormControl(
          {
            value: 0,
            disabled: true
          }
        ),
      }
    )
    this.enableMonthField(this.form);
    this.changeYearField(this.form);
    this.changeMonthField(this.form);
    this.initYears();
    this.initMonths();
    this.initDayTypes();
    this.initEmployeeById(this.employeeId);
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

  initYears() {
    this.years$ = this.userService.getAllYear().pipe(
      map(
        years => {
          return years.map(
            year => {
              const newYear = new Year(year.id,year.value);
              return newYear;
            }
          )
        }
      )
    )
    this.subscriptionYears = this.years$.subscribe(
      {
        next: resp => {
          this.years = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des années chargé.");
        }
      }
    )
  }

  initMonths() {
    this.months$ = this.userService.getAllMonth().pipe(
      map(
        months => {
          return months.map(
            month => {
              const newMonth = new Month(month.id,month.name,month.number);
              return newMonth;
            }
          )
        }
      )
    )
    this.subscriptionMonths = this.months$.subscribe(
      {
        next: resp => {
          this.months = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des mois chargé.");
        }
      }
    )
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

  loadEmployeeRecordings(employeeId: number, year: number, monthId: number) {
    this.recordings$ = this.userService.getAllEmployeeRecordings(employeeId, year, monthId).pipe(
      map(
        recordings => {
          return recordings.map(
            recording => {
              const newRecording = new Recording(recording.id, recording.date,recording.dateStart,recording.dateStop,recording.hourStart,recording.hourStop,recording.breakStart,recording.breakStop,recording.totalHours,recording.extraHours,recording.dueHours,recording.employeeId,recording.dayTypeId);              
              return newRecording;
            }
          )
        }
      )
    )
    this.subscriptionRecordings = this.recordings$.subscribe(
      {
        next: resp => {
          this.recordings = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des enregistrements de l'employé chargé.");
        }
      }
    )
  }

  loadDayType(dayTypeId: number): string {
    let loadDayType: DayType | undefined =  this.dayTypes.find(dayType => dayType.id === dayTypeId);
    if(loadDayType == undefined) {
      return "";
    } else {
      return loadDayType.name;
    }
  }

  enableMonthField(form: FormGroup) {
    form.get('year')?.valueChanges.subscribe(
      value => {       
        if (value === 0) {
          this.form.get('month')?.disable();
        } else {
          this.form.get('month')?.enable();
        }
      }
    );
  }

  changeYearField(form: FormGroup) {
    form.get('year')?.valueChanges.subscribe(
      year => {
        form.value.year = year;
        this.yearSelected = form.value.year;
        this.loadEmployeeRecordings(this.employeeId, this.yearSelected, this.monthSelected);        
      }
    )
  }

  changeMonthField(form: FormGroup) {
    form.get('month')?.valueChanges.subscribe(
      month => {
        form.value.month = month;
        this.monthSelected = form.value.month;
        this.loadEmployeeRecordings(this.employeeId, this.yearSelected, this.monthSelected);        
      }
    )
  }

}
