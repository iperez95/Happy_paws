import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { HomeComponent } from './components/home/home.component';
import { ProtectoraComponent } from './components/protectoras/protectora/protectora.component';
import { ListaProtectorasComponent } from './components/protectoras/lista-protectoras/lista-protectoras.component';
import { AltaProtectoraComponent } from './components/protectoras/alta-protectora/alta-protectora.component';
import { AnimalComponent } from './components/animales/animal/animal.component';
import { ListaAnimalesComponent } from './components/animales/lista-animales/lista-animales.component';
import { AltaAnimalComponent } from './components/animales/alta-animal/alta-animal.component';

const routes: Routes = [
  //animales
  { path: 'animales', component: AnimalComponent },
  { path: 'animales/listado', component:ListaAnimalesComponent},
  { path: 'animales/verUno/:id', component: AnimalComponent},
  { path: 'animales/gestion/alta', component: AltaAnimalComponent},

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
