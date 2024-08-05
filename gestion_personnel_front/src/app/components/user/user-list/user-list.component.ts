import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { User } from '../../../models/user/user';
import { Observable, Subscription, map } from 'rxjs';
import { AdminService } from '../../../services/admin/admin.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit, OnDestroy {

  title!: string;

  listUser!: User[];
  listUser$: Observable<User[]> | undefined;
  subscriptionListUser!: Subscription;

  constructor(
    private adminService: AdminService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.title = "Liste des utilisateurs du gestionnaire";
    this.loadUsers();
  }

  ngOnDestroy(): void {
    this.subscriptionListUser.unsubscribe();
  }

  loadUsers() {
    this.listUser$ = this.adminService.getAllUser().pipe(
      map(
        users => {
          return users.map(
            user => {
              const newUser = new User(user.id, user.secondname, user.firstname, user.roleName, user.enable, user.passwordUpdated);
              return newUser;
            }
          )
        }
      )
    );
    this.subscriptionListUser = this.listUser$.subscribe(
      {
        next: resp => {
          this.listUser = resp;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log("Utilisateurs chargés.");
        }
      }
    )
  }

  goToAddUser() {
    this.router.navigateByUrl('home/user/add');
  }

}
