import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalstorageService {

  constructor() { }
  
  getItem(name: string): string | null {
    return localStorage.getItem(name);
  }

  setItem(name: string, value: string) {
    localStorage.setItem(name,value);
  }

  removeItem(name: string) {
    localStorage.removeItem(name);
  }

  clear() {
    localStorage.clear();
  }
}
