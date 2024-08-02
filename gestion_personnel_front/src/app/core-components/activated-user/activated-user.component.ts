import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { catchError, concatMap, Observable, of, Subscription, switchMap } from 'rxjs';
import { EmailService } from '../../services/email/email.service';

@Component({
  selector: 'app-activated-user',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './activated-user.component.html',
  styleUrl: './activated-user.component.css'
})
export class ActivatedUserComponent implements OnInit {

  token!: string;
  text!: string;
  isUserActivated: boolean = false;


  constructor(
    private activatedRoute: ActivatedRoute,
    private authenticationService: AuthenticationService,
    private emailService: EmailService
  ) {

  }

  ngOnInit(): void {
    this.activatedUser();
  }
  
  activatedUser() {
    this.authenticationService.activatedUser(this.activatedRoute.snapshot.params['token']).pipe(
      switchMap(resp => {
        this.isUserActivated = resp;
        if (this.isUserActivated) {
          this.text = "Profil utilisateur activé. Un second email vous a été envoyé afin de créer votre nouveau mot de passe.";
          console.log(this.activatedRoute.snapshot.params['token']);
          
          return this.emailService.sendCreatedPasswordUserEmail(this.activatedRoute.snapshot.params['token']);
        } else {
          this.text = "Lien invalide ou périmé. Veuillez contacter l'équipe support.";
          return of(null);  // Retourne un observable vide si l'utilisateur n'est pas activé
        }
      }),
      catchError(err => {
        this.text = "Lien invalide ou périmé. Veuillez contacter l'équipe support.";
        console.log(err);
        return of(null);  // Retourne un observable vide en cas d'erreur
      })
    ).subscribe({
      next: resp => {
        if (resp) {
          console.log(resp);
        }
      },
      error: err => {
        console.log(err);
      },
      complete: () => {
        console.log("Processus d'activation du profil de l'utilisateur terminé.");
      }
    });
  }

}
