import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Animal} from 'src/app/entidades/animal';
import {Router} from '@angular/router';
import {AnimalService} from 'src/app/service/animal/animal.service';
import {MultimediaService} from "../../../service/multimedia/multimedia.service";
import {Multimedia} from "../../../entidades/multimedia";

@Component({
  selector: 'app-animal',
  templateUrl: './animal.component.html',
  styleUrls: ['./animal.component.css']
})
export class AnimalComponent {

  // Atributos

  @Input() public animal: Animal;
  @Input() public fotos: any;
  
  // Constructor

  constructor() {}

}
