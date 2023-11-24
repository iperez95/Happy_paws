import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';

@Component({
  selector: 'app-animaldetallado',
  templateUrl: './animaldetallado.component.html',
  styleUrls: ['./animaldetallado.component.css']
})
export class AnimaldetalladoComponent {


animal: Animal; 
multimedia: string;


  constructor(private router: Router) {}

  ngOnInit() {
  }

  getAnimalAge(fechaNacimiento: Date): number {
    const birthDate = new Date(fechaNacimiento);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
      age--;
    }
    return age;
  }
  
}
