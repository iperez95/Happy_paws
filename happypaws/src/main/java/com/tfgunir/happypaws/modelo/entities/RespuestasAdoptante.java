package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the respuestas_adoptante database table.
 * 
 */
@Entity
@Table(name="respuestas_adoptante")
@NamedQuery(name="RespuestasAdoptante.findAll", query="SELECT r FROM RespuestasAdoptante r")
public class RespuestasAdoptante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrespuesta;

	private String respuesta;

	//uni-directional many-to-one association to PreguntasAdoptante
	@ManyToOne
	@JoinColumn(name="IDPREGUNTA")
	private PreguntasAdoptante preguntasAdoptante;

	//uni-directional many-to-one association to Protectora
	@ManyToOne
	@JoinColumn(name="IDPROTECTORA")
	private Protectora protectora;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public RespuestasAdoptante() {
	}

	public int getIdrespuesta() {
		return this.idrespuesta;
	}

	public void setIdrespuesta(int idrespuesta) {
		this.idrespuesta = idrespuesta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public PreguntasAdoptante getPreguntasAdoptante() {
		return this.preguntasAdoptante;
	}

	public void setPreguntasAdoptante(PreguntasAdoptante preguntasAdoptante) {
		this.preguntasAdoptante = preguntasAdoptante;
	}

	public Protectora getProtectora() {
		return this.protectora;
	}

	public void setProtectora(Protectora protectora) {
		this.protectora = protectora;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}