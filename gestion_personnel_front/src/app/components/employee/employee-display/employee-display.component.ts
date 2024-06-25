import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-display',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './employee-display.component.html',
  styleUrl: './employee-display.component.css'
})
export class EmployeeDisplayComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
      
  }
}
