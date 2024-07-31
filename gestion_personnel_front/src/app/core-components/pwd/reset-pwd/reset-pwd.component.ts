import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ResetPwdFormComponent } from '../reset-pwd-form/reset-pwd-form.component';

@Component({
  selector: 'app-reset-pwd',
  standalone: true,
  imports: [CommonModule, ResetPwdFormComponent],
  templateUrl: './reset-pwd.component.html',
  styleUrl: './reset-pwd.component.css'
})
export class ResetPwdComponent implements OnInit {

  resetPwd!: boolean;

  constructor() {

  }

  ngOnInit(): void {
    this.resetPwd = true;
  }
}
