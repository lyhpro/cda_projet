import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../../models/employee/employee/employee';
import { Observable, Subscription } from 'rxjs';
import { UserService } from '../../services/user/user.service';
import { DayType } from '../../models/dayType/day-type';

@Component({
  selector: 'app-recording',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './recording.component.html',
  styleUrl: './recording.component.css'
})
export class RecordingComponent implements OnInit {

  employees!: Employee[];
  employees$: Observable<Employee[]> | undefined;
  subscriptionEmployees!: Subscription;

  dayTypes!: DayType[];
  dayTypes$: Observable<DayType[]> | undefined;
  subscriptiondayTypes!: Subscription;

  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.initEmployees();
    this.initDayTypes();
  }

  initEmployees() {
    this.employees$ = this.userService.getAllEmployee();
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
