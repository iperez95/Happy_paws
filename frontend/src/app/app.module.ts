import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './components/nav/nav.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContentComponent } from './components/content/content.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { AnimalesComponent } from './components/animales/animales.component';
import { ProtectorasComponent } from './components/Protectoras/protectoras.component';
import { ProtectoraComponent } from './components/Protectoras/protectora/protectora.component';
import { ListaProtectorasComponent } from './components/Protectoras/lista-protectoras/lista-protectoras.component';
import { HttpClientModule } from '@angular/common/http';
import { AltaProtectoraComponent } from './components/Protectoras/alta-protectora/alta-protectora.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    FooterComponent,
    ContentComponent,
    HomeComponent,
    LoginComponent,
    AnimalesComponent,
    ProtectorasComponent,
    ProtectoraComponent,
    ListaProtectorasComponent,
    AltaProtectoraComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      { path: 'animales', component: AnimalesComponent },
      { path: 'protectoras', component: ProtectorasComponent },
      { path: 'login', component: LoginComponent },
      { path: 'home', component: HomeComponent },
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
