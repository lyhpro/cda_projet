import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignupRequest } from '../../models/signup-request/signup-request';
import { Observable } from 'rxjs';
import { User } from '../../models/user/user';
import { Role } from '../../models/role/role';

const API_URL_ADMIN = 'http://localhost:8080/gestionnaire-personnel/admin/';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  signup(signupRequest: SignupRequest): Observable<User> {
    return this.http.post<User>(API_URL_ADMIN + 'signup', signupRequest);
  }

  getAllRole(): Observable<Role[]> {
    return this.http.get<Role[]>(API_URL_ADMIN + 'getAllRole');
  }

  getAllUser(): Observable<User[]> {
    return this.http.get<User[]>(API_URL_ADMIN + 'getAllUser');
  }
}
