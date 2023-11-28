import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { Router } from '@angular/router';
import { AnimalService } from 'src/app/service/animal/animal.service';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent implements OnInit{


  @Input() animal: Animal; multimedia: string;

  constructor(private router: Router) {}

  ngOnInit() {
  }

  public verDetallesAnimal(id: number) {
    this.router.navigate(['animales/verUno/', id]);
  }

  // getUrlForAnimal(id: number): string {
  //   return this.multimedia[id];
  // }
}
