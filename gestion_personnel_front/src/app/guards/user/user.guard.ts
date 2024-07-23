import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CommonService } from '../../services/common/common.service';

export const userGuard: CanActivateFn = (route, state) => {
  const commonService = inject(CommonService);
  const userRolename = commonService.getUserInCommonService().roleName;
  const arrayRole = route.data['roles'];
  
  if(arrayRole.includes(userRolename)) {
    return true;
  } else {
    return false;
  }

};
