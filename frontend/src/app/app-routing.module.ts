import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalesComponent } from './components/animales/animales.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ProtectoraComponent } from './components/Protectoras/protectora/protectora.component';
import { ProtectorasComponent } from './components/Protectoras/protectoras.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';

const routes: Routes = [
  //animales
  { path: 'animales', component: AnimalesComponent },
  //Protectoras
  { path: 'protectoras', component: ProtectorasComponent },
  { path: 'protectora', component: ProtectoraComponent },
  { path: 'protectoras/listado', component: ListaProtectorasComponent },
  { path: 'protectora/alta', component: AltaProtectoraComponent },
  
  //Login
  { path: 'login', component: LoginComponent },
  //Home - Inicio
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
