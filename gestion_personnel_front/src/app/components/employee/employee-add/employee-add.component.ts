import { Component, OnInit } from '@angular/core';
import { EmployeeForm } from '../../../models/employee/employee-form/employee-form';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ContractType } from '../../../models/contractType/contract-type';
import { Observable, Subscription } from 'rxjs';
import { Department } from '../../../models/department/department';
import { UserService } from '../../../services/user/user.service';
import { CommonModule } from '@angular/common';
import { HoursPerWeek } from '../../../models/hoursPerWeek/hours-per-week';

@Component({
  selector: 'app-employee-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee-add.component.html',
  styleUrl: './employee-add.component.css'
})
export class EmployeeAddComponent implements OnInit {

  employeForm!: EmployeeForm;
  form!: FormGroup;

  contractTypes!: ContractType[];
  contracTypes$: Observable<ContractType[]> | undefined;
  subscriptionContractTypes!: Subscription;

  departments!: Department[];
  departments$: Observable<Department[]> | undefined;
  subscriptionDepartments!: Subscription;

  hoursPerWeeks!: HoursPerWeek[];
  hoursPerWeeks$: Observable<HoursPerWeek[]> | undefined;
  subscriptionHoursPerWeeks!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.employeForm = new EmployeeForm(0,",","","",new Date(),"","",0,"",0,0,"",new Date(),new Date(),0,0,0,0);  
    this.form = this.formBuilder.group(
      {
        id: new FormControl(''),
        secondname: new FormControl(''),
        firstname: new FormControl(''),
        placeOfBirth: new FormControl(''),
        dateOfBirth: new FormControl(''),

        email: new FormControl(''),
        address: new FormControl(''),
        postalCode: new FormControl(''),
        city: new FormControl(''),
        homenumber: new FormControl(''),
        phonenumber: new FormControl(''),

        post: new FormControl(''),
        dateOfHiring: new FormControl(''),
        dateEndOfContract: new FormControl(''),
        salary: new FormControl(''),
        hoursPerWeekId: new FormControl(''),
        contractId: new FormControl(''),
        departmentId: new FormControl('')
      }
    )
    this.initListContractType();
    this.initListDepartment();
    this.initListHoursPerWeek();
  }

  initListContractType() {
    this.contracTypes$ = this.userService.getAllContractType();
    this.subscriptionContractTypes = this.contracTypes$.subscribe(
      {
        next: resp => {
          this.contractTypes = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des contrats chargé.");
        }
      }
    )
  }

  initListDepartment() {
    this.departments$ = this.userService.getAllDepartment();
    this.subscriptionDepartments = this.departments$.subscribe(
      {
        next: resp => {
          this.departments = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des departments chargé.");
        }
      }
    )
  }

  initListHoursPerWeek() {
    this.hoursPerWeeks$ = this.userService.getAllHoursPerWeek();
    this.subscriptionHoursPerWeeks = this.hoursPerWeeks$.subscribe(
      {
        next: resp => {
          this.hoursPerWeeks = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Liste des heures chargé.");
        }
      }
    )
  }

  onSubmit() {
    this.initEmployeeFormWithForm();
    this.createEmployee();
  }

  createEmployee() {
    this.userService.createEmploye(this.employeForm).subscribe();
    alert("Employe ajouté.");
    location.reload();
  }

  initEmployeeFormWithForm() {
    this.employeForm.id = this.form.value.id;
    this.employeForm.secondname = this.form.value.secondname,
    this.employeForm.firstname = this.form.value.firstname,
    this.employeForm.placeOfBirth = this.form.value.placeOfBirth,
    this.employeForm.dateOfBirth = this.form.value.dateOfBirth,

    this.employeForm.email = this.form.value.email,
    this.employeForm.address = this.form.value.address,
    this.employeForm.postalCode = this.form.value.postalCode,
    this.employeForm.city = this.form.value.city,
    this.employeForm.homenumber = this.form.value.homenumber,
    this.employeForm.phonenumber = this.form.value.phonenumber,

    this.employeForm.post = this.form.value.post,
    this.employeForm.dateOfHiring = this.form.value.dateOfHiring,
    this.employeForm.dateEndOfContract = this.form.value.dateEndOfContract,
    this.employeForm.salary = this.form.value.salary,
    this.employeForm.hoursPerWeekId = this.form.value.hoursPerWeekId,
    this.employeForm.contractId = this.form.value.contractId,
    this.employeForm.departmentId = this.form.value.departmentId
  }

}
