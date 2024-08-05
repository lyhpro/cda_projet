import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { SigninRequest } from '../../models/signin-request/signin-request';
import { JwtResponse } from '../../models/jwt-response/jwt-response';
import { Observable, Subscription, concatMap, map } from 'rxjs';
import { User } from '../../models/user/user';
import { Path } from '../../models/path/path';
import { LocalstorageService } from '../../services/localStorage/localstorage.service';
import { AuthenticationService } from '../../services/authentication/authentication.service';
import { CommonService } from '../../services/common/common.service';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { PopupDialogComponent } from '../popup-dialog/popup-dialog.component';
import { PopupService } from '../../services/popup/popup.service';

@Component({
  selector: 'app-topbar',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, MatDialogModule, MatButtonModule],
  templateUrl: './topbar.component.html',
  styleUrl: './topbar.component.css'
})
export class TopbarComponent implements OnInit {

  topbarTitle!: string;

  signinRequest!: SigninRequest;

  jwtResponse!: JwtResponse;
  jwtResponse$: Observable<JwtResponse> | undefined;

  subscriptionSignin!: Subscription;

  user!: User;
  user$: Observable<User> | undefined;
  subscriptionUser!: Subscription;

  menu!: Path[];
  menu$: Observable<Path[]> | undefined;
  subscriptionMenu!: Subscription;

  constructor(
    private commonService: CommonService,
    private authenticationService: AuthenticationService,
    private localstorageService: LocalstorageService, 
    private popupService: PopupService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.topbarTitle = "Gestionnaire de personnel";
    this.signinRequest = new SigninRequest("","");
    this.jwtResponse = new JwtResponse("");
    if(this.jwtIsPresent()) {
      this.loadEnvironment();
    }
  }

  onSubmit() {
    this.signIn(this.signinRequest);
  }

  signIn(signinRequest: SigninRequest) {
    this.jwtResponse$ = this.authenticationService.signin(signinRequest);
    this.subscriptionSignin = this.jwtResponse$.subscribe(
      {
        next: resp => {
          this.jwtResponse = resp;
          if(this.jwtResponse.jwt == "") {
            alert("Erreur.");
          } else {
            this.localstorageService.setItem("userJwt",this.jwtResponse.jwt);
            this.popupService.openPopup("Connexion réussie",2000,true)
          }
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Fin signin.");
        }
      }
    )
  }

  signOut() {
    this.popupService.openPopup("Déconnexion réussie",2000,false);
    setTimeout(
      () => {
        this.menu = [];
        this.user = new User(0,"","","",false,false);
        this.commonService.setUserInCommonService(this.user);
        this.localstorageService.removeItem("userJwt");
        this.subscriptionMenu.unsubscribe();
        this.goToPage('auth');
      },2100
    )
    setTimeout(
      () => {
        location.reload();
      },2200
    )
  }

  loadEnvironment() {
    this.menu$ = this.commonService.getUser().pipe(
      concatMap(
        user => {
          this.user = new User(user.id, user.secondname, user.firstname, user.roleName, user.enable, user.passwordUpdated);
          this.commonService.setUserInCommonService(this.user);
          if(this.user.enable) {
            this.goToPage('home');
          } else {
            this.goToPage('first-connection');
          }
          return this.commonService.getMenu(this.user.roleName).pipe(
            map(
              routes => {
                return routes.map(
                  route => {
                    const newRoute = new Path(route.id,route.name,route.urlPath,route.alias,route.enable,route.listIdPathAssigned);
                    return newRoute;
                  }
                )
              }
            )
          ); 
        }
      )
    );
    this.subscriptionMenu = this.menu$.subscribe(
      {
        next: resp => {
          if(this.user.enable) {
            this.menu = resp;
          } else {
            this.menu = [];
          }
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Environnement chargé.");
        }
      }
    );

  }

  jwtIsPresent(): boolean {
    if(this.localstorageService.getItem("userJwt") != null) {
      return true;
    }
    return false;
  }

  goToPage(url: string) {
    this.router.navigateByUrl(url);    
  }

}
