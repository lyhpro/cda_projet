import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PwdForm } from '../../../models/pwdForm/pwd-form';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { PopupService } from '../../../services/popup/popup.service';

@Component({
  selector: 'app-reset-pwd-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-pwd-form.component.html',
  styleUrl: './reset-pwd-form.component.css'
})
export class ResetPwdFormComponent implements OnInit {

  @Input() createdPwd: boolean = false;
  @Input() resetPwd: boolean = false;

  pwdFormGroup!: FormGroup;
  pwdForm!: PwdForm;
  title!: string;
  tokenId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private popupService: PopupService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    console.log(this.createdPwd);
    console.log(this.resetPwd);

    this.pwdForm = new PwdForm(0,"","");

    this.initTitle();
    this.pwdFormGroup = this.initPwdForm();
    
  }

  initTitle() {
    if(this.createdPwd && !this.resetPwd) {
      this.title = "Veuillez créer votre nouveau mot de passe";
    } else if(!this.createdPwd && this.resetPwd) {
      this.title = "Veuillez réinitialiser votre mot de passe";
    } else {
      this.title = "";
    }
  }

  initPwdForm() {
    this.tokenId = this.activatedRoute.snapshot.params['tokenId'];
    return this.formBuilder.group(
      {
        tokenId: new FormControl(this.tokenId, Validators.required),
        password: new FormControl('', Validators.required),
        confirmedPassword: new FormControl('', Validators.required)
      }
    )
  }

  onSubmit() {
    this.pwdForm.tokenId = this.pwdFormGroup.value.tokenId;
    this.pwdForm.password = this.pwdFormGroup.value.password;
    this.pwdForm.confirmedPassword = this.pwdFormGroup.value.confirmedPassword;
    
    if(this.createdPwd && !this.resetPwd) {
      this.authenticationService.updatePwdUser(this.pwdForm).subscribe(
        {
          next: resp => {
            if(resp) {
              this.popupService.openPopup("Mot de passe modifié avec succès.", 2000, false);
              setTimeout(
                () => {
                  this.router.navigateByUrl('auth');
                },2100
              )
            } else {
              this.popupService.openPopup("Mot de pass incorrecte. Veuillez réessayer.", 2000, false);
            }
          },
          error: err => {
            console.log(err);
            
          },
          complete: () => {
            console.log("onSubmit update terminé.");
            
          }
        }
      )
    } else if(!this.createdPwd && this.resetPwd) {
      this.authenticationService.resetPwdUser(this.pwdForm).subscribe(
        {
          next: resp => {
            if(resp) {
              this.popupService.openPopup("Mot de passe modifié avec succès.", 2000, false);
              setTimeout(
                () => {
                  this.router.navigateByUrl('auth');
                },2100
              )
            } else {
              this.popupService.openPopup("Mot de pass incorrecte. Veuillez réessayer.", 2000, false);
            }
          },
          error: err => {
            console.log(err);
            
          },
          complete: () => {
            console.log("onSubmit resete terminé.");
            
          }
        }
      )
    }
  }

  cancel() {
    this.pwdFormGroup.patchValue(
      {
        tokenId: 0,
        password: '',
        confirmedPassword: ''
      }
    )
  }
}
