import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LocalstorageService } from '../../services/localStorage/localstorage.service';

export const authenticationGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const localsotrageService = inject(LocalstorageService);
  const localData = localsotrageService.getItem("userJwt");

  if(localData != null) {
    return true;
  } else {    
    router.navigateByUrl('auth');
    return false;
  }
};
