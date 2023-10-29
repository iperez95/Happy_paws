import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginModalComponent } from 'src/app/components/login-modal/login-modal.component';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  constructor(private dialog: MatDialog) {}

  openDialog() {
    let dialogRef = this.dialog.open(LoginModalComponent, {
      height: '500px',
      width: '1200px',
    });
  
  }
}