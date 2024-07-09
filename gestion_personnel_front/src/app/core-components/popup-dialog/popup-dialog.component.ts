import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-popup-dialog',
  standalone: true,
  imports: [],
  templateUrl: './popup-dialog.component.html',
  styleUrl: './popup-dialog.component.css'
})
export class PopupDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<PopupDialogComponent>) {}

  close() {
    this.dialogRef.close();
    location.reload();

  }

  ngOnInit(): void {
      
  }
}
