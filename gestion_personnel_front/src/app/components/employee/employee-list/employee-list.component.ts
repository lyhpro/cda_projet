import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { EmployeeForm } from '../../../models/employee/employee-form/employee-form';
import { UserService } from '../../../services/user/user.service';
import { Department } from '../../../models/department/department';
import { ContractType } from '../../../models/contractType/contract-type';
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {
      
  }

  goToAddEmployee() {
    this.router.navigateByUrl('home/employee/add');
  }

}
