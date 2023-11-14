import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Animal } from 'src/app/entidades/animal';
import { AnimalService } from 'src/app/service/Animal/animal.service';

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent implements OnInit{

  id:number;
  animal : Animal;
  route: ActivatedRoute;

  constructor(private _animalService: AnimalService, route: ActivatedRoute) {
    this.route = route;
  }




  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
}
