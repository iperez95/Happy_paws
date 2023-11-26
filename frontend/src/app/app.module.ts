import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContentComponent } from './components/content/content.component';
import { HomeComponent } from './components/home/home.component';
import { LoginModalComponent } from './components/login-modal/login-modal.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import {MatTabsModule} from '@angular/material/tabs';
import { MunicipiosComponent } from './components/municipios/municipios.component';
import { ProvinciasComponent } from './components/provincias/provincias.component';
import { DetalleProtectoraComponent } from './components/Protectoras/detalle-protectora/detalle-protectora.component';
import { ModificarProtectoraComponent } from './components/Protectoras/gestion/modificar-protectora/modificar-protectora.component';
import { GestionProtectoraComponent } from './components/Protectoras/gestion/gestion-protectora/gestion-protectora.component';
import { ContactoComponent } from './components/contacto/contacto.component';
import { ContactoProtectoraComponent } from './components/Protectoras/contacto-protectora/contacto-protectora.component';
import { RegistroComponent } from './components/registro/registro.component';
import { RegistroProtectoraComponent } from './components/registro-protectora/registro-protectora.component';
import { AnimalComponent } from './components/animales/animal/animal.component';
import { ListaAnimalesComponent } from './components/animales/lista-animales/lista-animales.component';
import { AltaAnimalComponent } from './components/animales/gestion/alta-animal/alta-animal.component';
import { CuestionarioAdopcionComponent } from './components/cuestionario/cuestionario-adopcion/cuestionario-adopcion.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
//import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';
import { PetecionesAdopcionComponent } from './components/peteciones-adopcion/peteciones-adopcion.component';
import { MatChipsModule } from '@angular/material/chips';
import { AdopcionesCompletadasComponent } from './components/adopciones-completadas/adopciones-completadas.component';
import { PerfilUsuarioComponent } from './components/perfil-usuario/perfil-usuario.component';
import { AnimaldetalladoComponent } from './components/animales/animaldetallado/animaldetallado.component';
import { SubirFotoComponent } from './components/Protectoras/gestion/subir-foto/subir-foto.component';
import { PanelAdminComponent } from './components/panel-admin/panel-admin.component';
import { GestionAnimalComponent } from './components/animales/gestion/gestion-animal/gestion-animal.component';
import { ModificarAnimalComponent } from './components/animales/gestion/modificar-animal/modificar-animal.component';
import { SubirfotoAnimalComponent } from './components/animales/gestion/subirfoto-animal/subirfoto-animal.component';
import { ListadoGestionProtectorasComponent } from './components/panel-admin/Protectoras/listado-gestion-protectoras/listado-gestion-protectoras.component';
import { EditarProtectoraGestionComponent } from './components/panel-admin/Protectoras/editar-protectora-gestion/editar-protectora-gestion.component';
import { ModalCuestionarioComponent } from './components/modal-cuestionario/modal-cuestionario.component';
import { AdopcionesUsuarioComponent } from './components/adopciones-usuario/adopciones-usuario.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FooterComponent,
    ContentComponent,
    HomeComponent,
    LoginModalComponent,
    ListaProtectorasComponent,
    //AltaProtectoraComponent,
    MunicipiosComponent,
    ProvinciasComponent,
    AnimalComponent,
    ListaAnimalesComponent,
    AltaAnimalComponent,
    DetalleProtectoraComponent,
    ModificarProtectoraComponent,
    GestionProtectoraComponent,
    ContactoComponent,
    ContactoProtectoraComponent,
    RegistroComponent,
    RegistroProtectoraComponent,
    CuestionarioAdopcionComponent,
    PetecionesAdopcionComponent,
    AdopcionesCompletadasComponent,
    PerfilUsuarioComponent,
    AnimaldetalladoComponent,
    SubirFotoComponent,
    PanelAdminComponent,
    GestionAnimalComponent,
    ModificarAnimalComponent,
    SubirfotoAnimalComponent,
    ListadoGestionProtectorasComponent,
    EditarProtectoraGestionComponent,
    ModalCuestionarioComponent,
    AdopcionesUsuarioComponent,
  ],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatTabsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatChipsModule,
    ReactiveFormsModule,
    MatDialogModule,
    RouterModule.forRoot([
      { path: 'animales', component: ListaAnimalesComponent},
      { path: 'home', component: HomeComponent },
    ]),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
