import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { SignupRequest } from '../../../models/signup-request/signup-request';
import { Role } from '../../../models/role/role';
import { Observable, Subscription, map } from 'rxjs';
import { AdminService } from '../../../services/admin/admin.service';
import { PopupService } from '../../../services/popup/popup.service';

@Component({
  selector: 'app-user-add',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './user-add.component.html',
  styleUrl: './user-add.component.css'
})
export class UserAddComponent implements OnInit, OnDestroy {

  title!: string;

  signupForm!: FormGroup;
  signup!: SignupRequest;

  listRole!: Role[];
  listRole$: Observable<Role[]> | undefined;
  subscriptionListRole!: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private adminService: AdminService,
    private popupService: PopupService
  ) {}

  ngOnInit(): void {
    this.title = "Ajout d'un utilisateur";
    this.signup = new SignupRequest("","","","","");
    this.signupForm = this.formBuilder.group(
      {
        secondname: ['', Validators.required],
        firstname: ['', Validators.required],
        email: ['', Validators.required],
        pwd: [''],
        roleName: ['', Validators.required]
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
    this.adminService.signup(this.signup).subscribe(
      {
        next: resp => {
          console.log(resp);
        },
        error: err => {
          console.log(err);
          this.popupService.openPopup("Erreur, utilisateur déja crée",2000, false);
        },
        complete: () => {
          this.popupService.openPopup("Utilisateur ajouté",2000,true);
        }
      }
    );
  }

  initSignupRequestWithForm() {
    this.signup.secondname = this.signupForm.value.secondname;
    this.signup.firstname = this.signupForm.value.firstname;
    this.signup.email = this.signupForm.value.email;
    this.signup.pwd = "";
    this.signup.roleName = this.signupForm.value.roleName;
  }
  
}
