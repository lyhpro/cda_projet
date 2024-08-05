import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const API_URL_EMAIL = 'http://localhost:8080/gestionnaire-personnel/email/';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  constructor(
    private http: HttpClient
  ) { }

  sendCreatedPasswordUserEmail(tokenId: number): Observable<string> {
    return this.http.get<string>(API_URL_EMAIL + 'sendUpdatePasswordUserEmail/' + tokenId);
  }
}
