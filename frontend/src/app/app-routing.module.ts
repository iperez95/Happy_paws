import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { HomeComponent } from './components/home/home.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
//import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';
import { DetalleProtectoraComponent } from './components/Protectoras/detalle-protectora/detalle-protectora.component';
import { ModificarProtectoraComponent } from './components/Protectoras/gestion/modificar-protectora/modificar-protectora.component';
import { GestionProtectoraComponent } from './components/Protectoras/gestion/gestion-protectora/gestion-protectora.component';
import { ContactoComponent } from './components/contacto/contacto.component';
import { ContactoProtectoraComponent } from './components/Protectoras/contacto-protectora/contacto-protectora.component';
import { RegistroComponent } from './components/registro/registro.component';
import { RegistroProtectoraComponent } from './components/registro-protectora/registro-protectora.component';
import { AnimalComponent } from './components/animales/animal/animal.component';
import { ListaAnimalesComponent } from './components/animales/lista-animales/lista-animales.component';
import { CuestionarioAdopcionComponent } from './components/cuestionario/cuestionario-adopcion/cuestionario-adopcion.component';
import { PerfilUsuarioComponent } from './components/perfil-usuario/perfil-usuario.component';
import { SubirFotoComponent } from './components/Protectoras/gestion/subir-foto/subir-foto.component';
import { AnimaldetalladoComponent } from './components/animales/animaldetallado/animaldetallado.component';
import { PanelAdminComponent } from './components/panel-admin/panel-admin.component';
import { GestionAnimalComponent } from './components/animales/gestion/gestion-animal/gestion-animal.component';
import { AltaAnimalComponent } from './components/animales/gestion/alta-animal/alta-animal.component';
import { ModificarAnimalComponent } from './components/animales/gestion/modificar-animal/modificar-animal.component';
import { SubirfotoAnimalComponent } from './components/animales/gestion/subirfoto-animal/subirfoto-animal.component';
import { ListadoGestionProtectorasComponent } from './components/panel-admin/Protectoras/listado-gestion-protectoras/listado-gestion-protectoras.component';
import { EditarProtectoraGestionComponent } from './components/panel-admin/Protectoras/editar-protectora-gestion/editar-protectora-gestion.component';
import { SologatosComponent } from './components/animales/sologatos/sologatos.component';
import { SoloperrosComponent } from './components/animales/soloperros/soloperros.component';
import { ListadoGestionAnimalesComponent } from './components/panel-admin/Animales/listado-gestion-animales/listado-gestion-animales.component';
import { EditarAnimalGestionComponent } from './components/panel-admin/Animales/editar-animal-gestion/editar-animal-gestion.component';
import { EditarUsuarioComponent } from './components/panel-admin/Usuarios/editar-usuario/editar-usuario.component';
import { FaqComponent } from './components/faq/faq.component';
import { SobreNosotrosComponent } from './components/sobre-nosotros/sobre-nosotros.component';
import { ListadoAnimalesComponent } from './components/animales/listado-animales/listado-animales.component';


const routes: Routes = [
  //animales
  { path: 'animales/listado', component:ListaAnimalesComponent},
  { path: 'animales/listado2', component:ListadoAnimalesComponent},
  { path: 'animales/verUno/:id', component: AnimaldetalladoComponent},
  { path: 'animales/gestion/alta', component: AltaAnimalComponent},
  { path: 'animales/gestion', component: GestionAnimalComponent},
  { path: 'animales/gestion/modificar/:id', component: ModificarAnimalComponent},
  { path: 'animales/gestion/subirfotoanimal/:id', component: SubirfotoAnimalComponent},
  { path: 'animales/buscar/sologatos', component: SologatosComponent},
  { path: 'animales/buscar/soloperros', component: SoloperrosComponent},
  


  
 

  //Protectoras
  { path: 'protectora/gestion', component: GestionProtectoraComponent },
  { path: 'protectora/detalle/:id', component: DetalleProtectoraComponent },
  { path: 'protectora/todas', component: ListaProtectorasComponent },
  //{ path: 'protectora/alta', component: AltaProtectoraComponent },
  { path: 'protectora/gestion/modificar', component: ModificarProtectoraComponent},
  { path: 'protectora/contacto/:id', component: ContactoProtectoraComponent},
  { path: 'protectora/gestion/subirfoto', component: SubirFotoComponent},


  //Adoptante
  {path: 'adoptante/gestion', component: PerfilUsuarioComponent},
  //Cuestionario de adopción
  { path: 'cuestionario/adopcion', component: CuestionarioAdopcionComponent},

  //Admin
  { path: 'admin/gestion', component: PanelAdminComponent},
  { path: 'admin/gestion/protectoras', component: ListadoGestionProtectorasComponent},
  { path: 'admin/gestion/protectoras/editar/:id', component: EditarProtectoraGestionComponent},
  { path: 'admin/gestion/animales', component: ListadoGestionAnimalesComponent},
  { path: 'admin/gestion/animales/editar/:id', component: EditarAnimalGestionComponent},
  { path: 'admin/gestion/usuario/editar/:id', component: EditarUsuarioComponent},

  //Login
  { path: 'login', component: LoginModalComponent },
  { path: 'registro', component: RegistroComponent },
  {path: 'registro-protectora', component: RegistroProtectoraComponent},
  //Home - Inicio
  { path: 'contacto', component: ContactoComponent},
  {path: 'aboutus', component: SobreNosotrosComponent},
  { path: 'faq', component: FaqComponent},
  { path: '', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
