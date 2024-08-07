import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmailForm } from '../../../models/emailForm/email-form';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { PopupService } from '../../../services/popup/popup.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-forget-pwd',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './forget-pwd.component.html',
  styleUrl: './forget-pwd.component.css'
})
export class ForgetPwdComponent implements OnInit {

  title!: string;
  forgetpwdForm!: FormGroup;
  emailForm!: EmailForm;

  constructor(
    private formBuilder: FormBuilder,
    private authenticationSerivce: AuthenticationService,
    private popupService: PopupService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.title = "Mot de passe oublié ?";
    this.emailForm = new EmailForm("");
    this.forgetpwdForm = this.initForgetpwdForm();

  }

  initForgetpwdForm() {
    return this.formBuilder.group(
      {
        userEmail: new FormControl('', Validators.required)
      }
    )
  }

  onSubmit() {
    console.log(this.forgetpwdForm.value.userEmail);
    this.emailForm.userEmail = this.forgetpwdForm.value.userEmail;
    this.authenticationSerivce.resetPwdUserRequest(this.emailForm).subscribe(
      {
        next: resp => {
          if(resp) {
            this.popupService.openPopup("Votre demande de renouvellement de mot de passe a été envoyé.",2000,false);
            setTimeout(
              () => {
                this.router.navigateByUrl('auth');
              },2100
            )
          } else {
            this.popupService.openPopup("Adresse email incorrecte, veuillez réesayer.",2000,true);
          }
        },
        error: err => {
          console.log(err);
          
        },
        complete: () => {
          console.log("onSubmit terminé.");
          
        }
      }
    )
  }

}
