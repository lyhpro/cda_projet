import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-reset-pwd-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reset-pwd-form.component.html',
  styleUrl: './reset-pwd-form.component.css'
})
export class ResetPwdFormComponent implements OnInit {

  @Input() createdPwd: boolean = false;
  @Input() resetPwd: boolean = false;

  pwdForm!: FormGroup;
  title!: string;

  constructor(
    private formBuilder: FormBuilder
  ) {

  }

  ngOnInit(): void {
    console.log(this.createdPwd);
    console.log(this.resetPwd);

    this.initTitle();
    this.pwdForm = this.initPwdForm();
    
  }

  initTitle() {
    if(this.createdPwd && !this.resetPwd) {
      this.title = "Veuillez créer votre nouveau mot de passe";
    } else if(!this.createdPwd && this.resetPwd) {
      this.title = "Veuillez réinitialiser votre mot de passe";
    } else {
      this.title = "";
    }
  }

  initPwdForm() {
    return this.formBuilder.group(
      {
        pwd: new FormControl('', Validators.required),
        confirmedPwd: new FormControl('', Validators.required)
      }
    )
  }

  onSubmit() {
    if(this.createdPwd && !this.resetPwd) {
      
    } else if(!this.createdPwd && this.resetPwd) {

    }
  }

  cancel() {
    this.pwdForm.patchValue(
      {
        pwd: '',
        confirmedPwd: ''
      }
    )
  }
}
