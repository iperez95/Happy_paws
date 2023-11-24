import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/animal/animal.service';

@Component({
  selector: 'app-animaldetallado',
  templateUrl: './animaldetallado.component.html',
  styleUrls: ['./animaldetallado.component.css']
})
export class AnimaldetalladoComponent {


animal: Animal;

id: number;

//multimedia: string;


  constructor(private router: Router, private activateRouter: ActivatedRoute, private _animalService: AnimalService) {}

  ngOnInit() {
    this.activateRouter.params.subscribe((params) => {
      this.id = parseInt(params['id']);
      this.getAnimal();
    })
  }

  private getAnimal(): void {
    this._animalService.verAnimal(this.id).subscribe({
      next: animal => {
        this.animal = animal;
      },
      error: err => console.log(err)
    });
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
