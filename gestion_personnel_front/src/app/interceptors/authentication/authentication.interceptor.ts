import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const localData = localStorage.getItem('userJwt');

  if(
    req.url.endsWith('/signin') || 
    req.url.includes('/activatedUser') || 
    req.url.includes('/email') || 
    req.url.includes('/canUpdatePwdUser') || 
    req.url.includes('/updatePwdUser') ||
    req.url.includes('/resetPwdUser') ||
    req.url.includes('/resetPwdUserRequest')
    ) {
    const cloneRequest = req.clone(
      {
        setHeaders: {
          Authorization: ``
        }
      }
    );
  
    return next(cloneRequest);
  }

  const cloneRequest = req.clone(
    {
      setHeaders: {
        Authorization: `Bearer ${localData}`
      }
    }
  );

  return next(cloneRequest);
};