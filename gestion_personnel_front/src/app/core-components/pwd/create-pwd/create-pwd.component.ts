import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ResetPwdFormComponent } from '../reset-pwd-form/reset-pwd-form.component';
import { AuthenticationService } from '../../../services/authentication/authentication.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-pwd',
  standalone: true,
  imports: [CommonModule, ResetPwdFormComponent],
  templateUrl: './create-pwd.component.html',
  styleUrl: './create-pwd.component.css'
})
export class CreatePwdComponent implements OnInit {

  createdPwd!: boolean;
  tokenId!: number;
  text!: string;

  constructor(
    private authenticationService: AuthenticationService,
    private activatedRoute: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    this.canUpdatePwdUser();
  }

  canUpdatePwdUser() {
    this.tokenId = this.activatedRoute.snapshot.params['tokenId'];
    this.authenticationService.canUpdatePwdUser(this.tokenId).subscribe(
      {
        next: resp => {
          this.createdPwd = resp;          
          if(!this.createdPwd) {
            this.text = "Lien invalide ou périmé. Veuillez contacter l'équipe support.";
          }
        },
        error: err => {
          console.log(err);
          
        },
        complete: () => {
          console.log("canUpdatePwdUser terminée.");
          
        }
      }
    )
  }
}
