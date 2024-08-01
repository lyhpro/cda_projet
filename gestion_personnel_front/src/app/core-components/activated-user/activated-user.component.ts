import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../../services/authentication/authentication.service';

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
    private authenticationService: AuthenticationService
  ) {

  }

  ngOnInit(): void {
    this.activatedUser();
  }
  
  activatedUser() {
    // this.token = this.activatedRoute.snapshot.params['token'];
    this.authenticationService.activatedUser(this.activatedRoute.snapshot.params['token']).subscribe(
      {
        next: resp => {
          this.isUserActivated = resp;
          if(this.isUserActivated) {
            this.text = "Profil utilisateur activé. Un second email vous a été envoyé afin de créer votre nouveau mot de passe.";
          } else {
            this.text = "Lien invalide ou périmée. Veuillez contacter l'équipe support.";
          }
        }, 
        error: err => {
          this.text = "Lien invalide ou périmée. Veuillez contacter l'équipe support.";
          console.log(err);
        },
        complete: () => {
          console.log("Processus d'activation du profil de l'utilisateur terminé.");
        }
      }
    )
  }
}
