import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { SignupRequest } from '../../../models/signup-request/signup-request';
import { Role } from '../../../models/role/role';
import { Observable, Subscription, map } from 'rxjs';
import { AdminService } from '../../../services/admin/admin.service';

@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-add.component.html',
  styleUrl: './user-add.component.css'
})
export class UserAddComponent implements OnInit, OnDestroy {

  signupForm!: FormGroup;
  signup!: SignupRequest;

  listRole!: Role[];
  listRole$: Observable<Role[]> | undefined;
  subscriptionListRole!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {
    this.signup = new SignupRequest("","","","","");
    this.signupForm = this.formBuilder.group(
      {
        secondname: new FormControl(''),
        firstname: new FormControl(''),
        email: new FormControl(''),
        pwd: new FormControl(''),
        roleName: new FormControl('')
      }
    )
    this.initListRole();  
  }

  ngOnDestroy(): void {
    this.subscriptionListRole.unsubscribe();
  }

  onSubmit() {
    this.initSignupRequestWithForm();
    this.signUp();
  }

  initListRole() {
    this.listRole$ = this.adminService.getAllRole().pipe(
      map(
        roles => {
          return roles.map(
            role => {
              const newRole = new Role(role.id, role.name, role.description, role.enable);
              return newRole;
            }
          )
        }
      )
    );
    this.subscriptionListRole = this.listRole$.subscribe(
      {
        next : resp => {
          this.listRole = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Roles chargé.");
        }
      }
    )
  }

  signUp() {
    this.adminService.signup(this.signup).subscribe();
    alert("Utilisateur ajouté.");
    location.reload();
  }

  initSignupRequestWithForm() {
    this.signup.secondname = this.signupForm.value.secondname;
    this.signup.firstname = this.signupForm.value.firstname;
    this.signup.email = this.signupForm.value.email;
    this.signup.pwd = "";
    this.signup.roleName = this.signupForm.value.roleName;
  }
  
}
