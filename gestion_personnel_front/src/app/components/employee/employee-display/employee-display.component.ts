import { CommonModule, Time } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user/user.service';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Employee } from '../../../models/employee/employee/employee';
import { Observable, Subscription, map, window } from 'rxjs';
import { Month } from '../../../models/month/month';
import { Recording } from '../../../models/recording/recording';
import { DayType } from '../../../models/dayType/day-type';
import { HoursFormatPipe } from '../../../pipes/hours/hours-format.pipe';
import { DurationsFormatPipe } from '../../../pipes/durations/durations-format.pipe';
import { Year } from '../../../models/year/year';
import { PopupService } from '../../../services/popup/popup.service';

@Component({
  selector: 'app-employee-display',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HoursFormatPipe, DurationsFormatPipe],
  templateUrl: './employee-display.component.html',
  styleUrl: './employee-display.component.css'
})
export class EmployeeDisplayComponent implements OnInit {

  form!: FormGroup;
  nbDayForm!: FormGroup;
  employeeId!: number;
  dayNameTitle!: string;

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

  nbSpecialDays!: Map<string,number[]>;
  nbSpecialDays$: Observable<Map<string,number[]>> | undefined;
  subscriptionNbSpecialDays!: Subscription;

  recordings!: Recording[];
  recordings$: Observable<Recording[]> | undefined;
  subscriptionRecordings!: Subscription;

  dayTypes!: DayType[];
  dayTypes$: Observable<DayType[]> | undefined;
  subscriptiondayTypes!: Subscription;

  employeeTotalWorkingHours!: Map<string,string[]>;
  employeeTotalWorkingHours$: Observable<Map<string,string[]>> | undefined;
  subscriptionEmployeeTotalWorkingHours!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService,
    private popupService: PopupService
  ) {}

  ngOnInit(): void {
    this.recordings = [];
    this.monthSelected = 0;
    this.yearSelected = 0;
    this.dayNameTitle = '';
    this.employee = new Employee(0,"","","",new Date(),false,new Date(),0,0);
    this.employeeId = this.activatedRoute.snapshot.params['id'];
    this.form = this.formBuilder.group(
      {
        fullname: [{value: '',disabled: true}],
        year: new FormControl(0),
        month: new FormControl(0),
      }
    )
    this.nbDayForm = this.formBuilder.group(
      {
        employeeId: new FormControl(0),
        dayName: new FormControl(''),
        nbDay: new FormControl(1)
      }
    )
    // this.enableMonthField(this.form);
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
          this.form.patchValue(
            {
              fullname: this.employee.firstname + " " + this.employee.secondname
            }
          )
          this.nbDayForm.patchValue(
            {
              employeeId: this.employee.id
            }
          )
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

  loadEmployeeNbHolidayRttIllness(employeeId: number, yearId: number, monthId: number) {
    this.nbSpecialDays$ = this.userService.getNbSpecialDay(employeeId,yearId,monthId);
    this.subscriptionEmployee = this.nbSpecialDays$.subscribe(
      {
        next: resp => {
          this.nbSpecialDays = resp;          
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste du nombre de jour de vacance, rtt et maladie de l'meployé chargé.");
        }
      }
    )
  }

  loadEmployeeTotalWorkingHours(employeeId: number, yearId: number, monthId: number) {
    this.employeeTotalWorkingHours$ =  this.userService.getEmployeeWorkingHours(employeeId,yearId,monthId);
    this.subscriptionEmployeeTotalWorkingHours = this.employeeTotalWorkingHours$.subscribe(
      {
        next: resp => {
          this.employeeTotalWorkingHours = resp;          
        },
        error: err => {
          console.log(err);
        },
        complete: () => { 
          console.log("Total des heures de travail de l'employé chargé");
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

  onSubmit() {
    this.userService.addDaysToEmployeeSpecialDay(this.nbDayForm.value.employeeId, this.nbDayForm.value.dayName, this.nbDayForm.value.nbDay).subscribe();
    this.popupService.openPopup("Jours ajoutés",2000,true);
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
        this.yearSelected = year;
        if(this.yearSelected != 0 && this.monthSelected != 0) {
          this.loadEmployeeRecordings(this.employeeId, this.yearSelected, this.monthSelected);  
          this.loadEmployeeNbHolidayRttIllness(this.employeeId, this.yearSelected, this.monthSelected);     
          this.loadEmployeeTotalWorkingHours(this.employeeId, this.yearSelected, this.monthSelected);
        }
      }
    )
  }

  changeMonthField(form: FormGroup) {
    form.get('month')?.valueChanges.subscribe(
      month => {
        form.value.month = month;
        this.monthSelected = form.value.month;
        if(this.yearSelected != 0 && this.monthSelected != 0) {
          this.loadEmployeeRecordings(this.employeeId, this.yearSelected, this.monthSelected); 
          this.loadEmployeeNbHolidayRttIllness(this.employeeId, this.yearSelected, this.monthSelected); 
          this.loadEmployeeTotalWorkingHours(this.employeeId, this.yearSelected, this.monthSelected);      
        }
      }
    )
  }

  displayYearValue(yearId: number): number {
    let yearValue =  this.years.find(year => year.id == yearId)?.value;
    if(yearValue != undefined) {
      return yearValue;
    } else {
      return 0;
    }
  }

  displayMonthName(monthId: number): string {
    let monthName = this.months.find(month => month.id == monthId)?.name;
    if(monthName != undefined) {
      return monthName;
    } else {
      return "";
    }
  }

  loadDayName(dayName: string) {
    this.nbDayForm.patchValue(
      {
        dayName: dayName,
        nbDay: 1
      }
    )
    if(dayName == 'vacance') {
      this.dayNameTitle = 'jours de congé';
    } else if(dayName == 'rtt') {
      this.dayNameTitle = 'jours de RTT';
    } else if(dayName == 'maladie') {
      this.dayNameTitle = 'jours de congé maladie';
    }
  }

  resetNbDayForm() {
    this.nbDayForm.patchValue(
      {
        employeeId: 0,
        dayName: '',
        nbDay: 1
      }
    )
  }

}
