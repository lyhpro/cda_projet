import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent implements OnInit {

  title!: string;

  constructor() {}

  ngOnInit(): void {
    this.title = "Bienvenue sur le gestionnaire de personnel";
  }

}
