import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-forget-pwd',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './forget-pwd.component.html',
  styleUrl: './forget-pwd.component.css'
})
export class ForgetPwdComponent implements OnInit {

  title!: string;
  forgetpwdForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.title = "Mot de passe oublié ?";
    this.forgetpwdForm = this.initForgetpwdForm();

  }

  initForgetpwdForm() {
    return this.formBuilder.group(
      {
        userEmail: new FormControl('', Validators.required)
      }
    )
  }

  onSubmit() {
    console.log(this.forgetpwdForm.value.userEmail);
    
  }

}
