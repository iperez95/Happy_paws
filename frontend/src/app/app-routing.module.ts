import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalesComponent } from './components/animales/animales.component';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { HomeComponent } from './components/home/home.component';
import { ProtectoraComponent } from './components/Protectoras/protectora/protectora.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';

const routes: Routes = [
  //animales
  { path: 'animales', component: AnimalesComponent },
  //Protectoras
  { path: 'protectora/:id', component: ProtectoraComponent },
  { path: 'protectoras', component: ListaProtectorasComponent },
  { path: 'protectora/alta', component: AltaProtectoraComponent },
  
  //Login
  { path: 'login', component: LoginModalComponent },
  //Home - Inicio
  { path: '', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
