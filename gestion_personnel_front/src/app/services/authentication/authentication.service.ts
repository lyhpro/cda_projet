import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SigninRequest } from '../../models/signin-request/signin-request';
import { Observable } from 'rxjs';
import { JwtResponse } from '../../models/jwt-response/jwt-response';
import { PwdForm } from '../../models/pwdForm/pwd-form';
import { EmailForm } from '../../models/emailForm/email-form';

const API_URL_AUTH = 'http://localhost:8080/gestionnaire-personnel/authentication/';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {}

  signin(signinRequest: SigninRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(API_URL_AUTH + 'signin', signinRequest);
  }

  activatedUser(tokenId: number): Observable<boolean> {
    return this.http.get<boolean>(API_URL_AUTH + 'activatedUser/' + tokenId);
  }

  canUpdatePwdUser(tokenId: number): Observable<boolean> {
    return this.http.get<boolean>(API_URL_AUTH + 'canUpdatePwdUser/' + tokenId);
  }

  updatePwdUser(pwdForm: PwdForm): Observable<boolean> {
    return this.http.post<boolean>(API_URL_AUTH + 'updatePwdUser', pwdForm);
  }

  resetPwdUser(pwdForm: PwdForm): Observable<boolean> {
    return this.http.post<boolean>(API_URL_AUTH + 'resetPwdUser', pwdForm);
  }
  
  resetPwdUserRequest(emailForm: EmailForm): Observable<boolean> {
    return this.http.post<boolean>(API_URL_AUTH + 'resetPwdUserRequest', emailForm)
  }
  
}
