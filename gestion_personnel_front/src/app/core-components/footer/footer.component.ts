import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent implements OnInit {
  
  footerTitle!: string;

  constructor() {}

  ngOnInit(): void {
    this.footerTitle = "Gestionnaire de personnel - Heunpha-Ly LY";
  }
  
}
