import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ResetPwdFormComponent } from '../reset-pwd-form/reset-pwd-form.component';

@Component({
  selector: 'app-create-pwd',
  standalone: true,
  imports: [CommonModule, ResetPwdFormComponent],
  templateUrl: './create-pwd.component.html',
  styleUrl: './create-pwd.component.css'
})
export class CreatePwdComponent implements OnInit {

  createdPwd!: boolean;

  constructor() {

  }

  ngOnInit(): void {
    this.createdPwd = true;
  }
}
