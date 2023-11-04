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
import { AnimalesComponent } from './components/animales/animales.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
import { HttpClientModule } from '@angular/common/http';
import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';
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


@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FooterComponent,
    ContentComponent,
    HomeComponent,
    AnimalesComponent,
    LoginModalComponent,
    ListaProtectorasComponent,
    AltaProtectoraComponent,
    MunicipiosComponent,
    ProvinciasComponent,
    DetalleProtectoraComponent,
    ModificarProtectoraComponent,
    GestionProtectoraComponent,
  
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
    MatDialogModule,
    RouterModule.forRoot([
      { path: 'animales', component: AnimalesComponent },
      { path: 'home', component: HomeComponent },
    ]),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
