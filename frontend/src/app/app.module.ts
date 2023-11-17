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
import { ProtectoraComponent } from './components/protectoras/protectora/protectora.component';
import { ListaProtectorasComponent } from './components/protectoras/lista-protectoras/lista-protectoras.component';
import { HttpClientModule } from '@angular/common/http';
import { AltaProtectoraComponent } from './components/protectoras/alta-protectora/alta-protectora.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MunicipiosComponent } from './components/municipios/municipios.component';
import { ProvinciasComponent } from './components/provincias/provincias.component';
import { AnimalComponent } from './components/animales/animal/animal.component';
import { ListaAnimalesComponent } from './components/animales/lista-animales/lista-animales.component';
import { AltaAnimalComponent } from './components/animales/alta-animal/alta-animal.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FooterComponent,
    ContentComponent,
    HomeComponent,
    LoginModalComponent,
    ProtectoraComponent,
    ListaProtectorasComponent,
    AltaProtectoraComponent,
    MunicipiosComponent,
    ProvinciasComponent,
    AnimalComponent,
    ListaAnimalesComponent,
    AltaAnimalComponent
  ],
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule,
    RouterModule.forRoot([
      { path: 'animales', component: AnimalComponent },
      { path: 'home', component: HomeComponent },
    ]),
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
