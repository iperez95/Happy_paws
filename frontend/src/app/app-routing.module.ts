import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnimalesComponent } from './components/animales/animales.component';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { HomeComponent } from './components/home/home.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';
import { DetalleProtectoraComponent } from './components/Protectoras/detalle-protectora/detalle-protectora.component';
import { ModificarProtectoraComponent } from './components/Protectoras/gestion/modificar-protectora/modificar-protectora.component';
import { GestionProtectoraComponent } from './components/Protectoras/gestion/gestion-protectora/gestion-protectora.component';
import { ContactoComponent } from './components/contacto/contacto.component';
import { ContactoProtectoraComponent } from './components/Protectoras/contacto-protectora/contacto-protectora.component';

const routes: Routes = [
  //animales
  { path: 'animales', component: AnimalesComponent },
  
  //Protectoras
  { path: 'protectora/gestion/:id', component: GestionProtectoraComponent },
  { path: 'protectora/detalle/:id', component: DetalleProtectoraComponent },
  { path: 'protectora/todas', component: ListaProtectorasComponent },
  { path: 'protectora/alta', component: AltaProtectoraComponent },
  { path: 'protectora/gestion/modificar/:id', component: ModificarProtectoraComponent},
  { path: 'protectora/contacto/:id', component: ContactoProtectoraComponent},

  

  //Login
  { path: 'login', component: LoginModalComponent },
  //Home - Inicio
  { path: 'contacto', component: ContactoComponent},
  { path: '', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
