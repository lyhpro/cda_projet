import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PopupDialogComponent } from '../../core-components/popup-dialog/popup-dialog.component';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class PopupService {

  constructor(private dialog: MatDialog) { }

  openPopup(message: string, duration: number, reloadPage: boolean) {

    let dialogRef = this.dialog.open(PopupDialogComponent, {
      disableClose: true,
      width: '300px',
      data: { message }
    });


    setTimeout(() => {
      dialogRef.close();
      if(reloadPage) {
        location.reload();
      }
    }, duration);

  }
}
