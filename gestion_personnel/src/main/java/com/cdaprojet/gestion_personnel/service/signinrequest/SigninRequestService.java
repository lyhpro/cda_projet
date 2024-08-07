package com.cdaprojet.gestion_personnel.service.signinrequest;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;
import com.cdaprojet.gestion_personnel.model.emailForm.EmailForm;
import com.cdaprojet.gestion_personnel.model.pwdForm.PwdForm;

public interface SigninRequestService {
    
    JwtResponse signin(SigninRequest signinRequest);
    boolean activatedUser(long tokenId);
    boolean canUpdatePwdUser(long tokenId);
    boolean updatePwdUser(PwdForm pwdForm);
    boolean resetPwdUser(PwdForm pwdForm);
    boolean resetPwdUserRequest(EmailForm emailForm);

}