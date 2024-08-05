import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Path } from '../../models/path/path';
import { Observable } from 'rxjs';
import { User } from '../../models/user/user';

const API_URL_COMMON = 'http://localhost:8080/gestionnaire-personnel/common/';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  user: User = new User(0,"","","",false,false);

  constructor(private http: HttpClient) {}

  getMenu(nomRole: string): Observable<Path[]> {
    return this.http.get<Path[]>(API_URL_COMMON + 'loadMenu/byRoleName/' + nomRole);
  }

  getUser(): Observable<User> {
    return this.http.get<User>(API_URL_COMMON + 'getUser');
  }

  getUserInCommonService() {
    return this.user
  }

  setUserInCommonService(user: User) {
    this.user = user;
  }

}
