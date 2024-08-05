import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { catchError, concatMap, Observable, of, Subscription, switchMap } from 'rxjs';
import { EmailService } from '../../services/email/email.service';
import { PopupService } from '../../services/popup/popup.service';

@Component({
  selector: 'app-activated-user',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './activated-user.component.html',
  styleUrl: './activated-user.component.css'
})
export class ActivatedUserComponent implements OnInit {

  tokenId!: number;
  text!: string;
  isUserActivated: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private authenticationService: AuthenticationService
  ) {

  }

  ngOnInit(): void {
    this.activatedUser();
  }
  
  activatedUser() {
    this.tokenId = this.activatedRoute.snapshot.params['tokenId'];
    this.authenticationService.activatedUser(this.tokenId).subscribe(
      {
        next: resp=> {
          this.isUserActivated = resp;
          if(this.isUserActivated) {
            this.text = "Profil utilisateur activé. Un second email vous a été envoyé afin de créer votre nouveau mot de passe.";          
          } else {
            this.text = "Lien invalide ou périmé. Veuillez contacter l'équipe support.";
          }
        },
        error: err => {
          console.log(err);
          
        },
        complete: () => {
          console.log("Processus d'activation du profil user terminée");
        }
      }
    );
    
  }

}
