import { Component,Input, OnInit } from '@angular/core';
import { Protectora } from 'src/app/entidades/protectora';

@Component({
  selector: 'app-protectora',
  templateUrl: './protectora.component.html',
  styleUrls: ['./protectora.component.css']
})

export class ProtectoraComponent implements OnInit {

  // Este error es solo de compilacion, no afecta a la ejecucion
  @Input() protectora: Protectora = new Protectora();
  
  constructor () { }

  ngOnInit() {
    
    
  }


  }


