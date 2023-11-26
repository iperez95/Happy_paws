import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AxiosService } from 'src/app/service/axios/axios.service';
import { PreguntasAdoptante } from "src/app/entidades/preguntasAdoptante";
import { RespuestasAdoptante } from "src/app/entidades/respuestasAdoptante";
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/entidades/usuario';
import axios from 'axios';
import { VistaFormulario } from 'src/app/entidades/VistaFormulario';


@Component({
  selector: 'app-modal-cuestionario',
  templateUrl: './modal-cuestionario.component.html',
  styleUrls: ['./modal-cuestionario.component.css']
})
export class ModalCuestionarioComponent {
  userService: UsuarioService;
  respuestas: VistaFormulario[];

  constructor(
    public dialogRef: MatDialogRef<ModalCuestionarioComponent>,
    private axiosService: AxiosService,
    @Inject(MAT_DIALOG_DATA) public data:number
  ){}

  ngOnInit() {
    this.axiosService.request('GET', "cuestionario/verRespuestas/" + this.data, null).then((response) => {
      this.respuestas = response.data;
      console.log(this.respuestas);
    }).catch((error: any) => {
      console.log(error);
    });
  }

  onClose(): void {
    this.dialogRef.close();
  }

}
