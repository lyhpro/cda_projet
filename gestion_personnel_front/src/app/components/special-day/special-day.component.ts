import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-special-day',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './special-day.component.html',
  styleUrl: './special-day.component.css'
})
export class SpecialDayComponent implements OnInit {

  constructor() {}

  ngOnInit(): void {
      
  }
  
}
