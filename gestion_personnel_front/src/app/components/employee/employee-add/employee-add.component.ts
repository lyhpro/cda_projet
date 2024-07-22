import { Component, OnInit } from '@angular/core';
import { EmployeeForm } from '../../../models/employee/employee-form/employee-form';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ContractType } from '../../../models/contractType/contract-type';
import { Observable, Subscription, map } from 'rxjs';
import { Department } from '../../../models/department/department';
import { UserService } from '../../../services/user/user.service';
import { CommonModule } from '@angular/common';
import { HoursPerWeek } from '../../../models/hoursPerWeek/hours-per-week';
import { PopupService } from '../../../services/popup/popup.service';

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
    private userService: UserService,
    private popupService: PopupService
  ) {}

  ngOnInit(): void {
    this.employeForm = this.initEmptyEmployeeForm();
    this.form = this.initForm();
    this.initListContractType();
    this.initListDepartment();
    this.initListHoursPerWeek();
    
    this.resetDateFields();
  }

  initEmptyEmployeeForm() {
    return new EmployeeForm(0,",","","",new Date(),"","",0,"",0,0,"",new Date(),new Date(),0,0,0,0);
  }

  initForm() {
    return this.formBuilder.group(
      {
        id: new FormControl(0, Validators.required),
        secondname: new FormControl('', Validators.required),
        firstname: new FormControl('', Validators.required),
        placeOfBirth: new FormControl('', Validators.required),
        dateOfBirth: new FormControl('', Validators.required),

        email: new FormControl('', Validators.required),
        address: new FormControl('', Validators.required),
        postalCode: new FormControl('', Validators.required),
        city: new FormControl('', Validators.required),
        homenumber: new FormControl('', Validators.required),
        phonenumber: new FormControl('', Validators.required),

        post: new FormControl('', Validators.required),
        salary: new FormControl(0, Validators.required),
        hoursPerWeekId: new FormControl('', Validators.required),
        departmentId: new FormControl('', Validators.required),
        contractId: new FormControl('', Validators.required),
        dateOfHiring: new FormControl(''),
        dateEndOfContract: new FormControl('')
      }
    )
  }

  initListContractType() {
    this.contracTypes$ = this.userService.getAllContractType().pipe(
      map(
        contractTypes => {
          return contractTypes.map(
            contractType => {
              const newContractTypes = new ContractType(contractType.id,contractType.name,contractType.enable);
              return newContractTypes;
            }
          )
        }
      )
    );
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
    this.departments$ = this.userService.getAllDepartment().pipe(
      map(
        departments => {
          return departments.map(
            department => {
              const newDepartment = new Department(department.id,department.name,department.description,department.enable);
              return newDepartment;
            }
          )
        }
      )
    );
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
    this.hoursPerWeeks$ = this.userService.getAllHoursPerWeek().pipe(
      map(
        hoursPerWeeks => {
          return hoursPerWeeks.map(
            hoursPerWeek => {
              const newHoursPerWeek = new HoursPerWeek(hoursPerWeek.id,hoursPerWeek.hours,hoursPerWeek.enable);
              return newHoursPerWeek;
            }
          )
        }
      )
    );
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
    this.popupService.openPopup("Employé ajouté",2000,true);
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

  resetDateFields() {

    this.form.get('contractId')?.valueChanges.subscribe(
      contractId => {
        const dateOfHiringControl = this.form.get('dateOfHiring');
        dateOfHiringControl?.setValue('');
        dateOfHiringControl?.clearValidators();

        const dateEndOfContractControl = this.form.get('dateEndOfContract');
        dateEndOfContractControl?.setValue('');
        dateEndOfContractControl?.clearValidators();

        if(contractId == 1) {
          dateOfHiringControl?.setValidators([Validators.required]);
        } else if(contractId == 2) {
          dateOfHiringControl?.setValidators([Validators.required]);
          dateEndOfContractControl?.setValidators([Validators.required]);
        } 

        dateOfHiringControl?.updateValueAndValidity();
        dateEndOfContractControl?.updateValueAndValidity();

      }
    )
  }

  resetForm() {
    this.form.patchValue(
      {
        id: 0,
        secondname: '',
        firstname: '',
        placeOfBirth: '',
        dateOfBirth: '',

        email: '',
        address: '',
        postalCode: '',
        city: '',
        homenumber: '',
        phonenumber: '',

        post: '',
        salary: 0,
        hoursPerWeekId: '',
        departmentId: '',
        contractId: '',
        dateOfHiring: '',
        dateEndOfContract: ''
      }
    )
  }

}
