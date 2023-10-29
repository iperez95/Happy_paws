import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalesComponent } from './components/animales/animales.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ProtectoraComponent } from './components/Protectoras/protectora/protectora.component';
import { ProtectorasComponent } from './components/Protectoras/protectoras.component';

const routes: Routes = [
  { path: 'animales', component: AnimalesComponent },
  { path: 'protectoras', component: ProtectorasComponent },
  { path: 'protectora', component: ProtectoraComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
