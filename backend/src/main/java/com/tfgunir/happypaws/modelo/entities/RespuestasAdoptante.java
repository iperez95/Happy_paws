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

	public RespuestasAdoptante(int idrespuesta, String respuesta, PreguntasAdoptante preguntasAdoptante,
			Protectora protectora, Usuario usuario) {
		this.idrespuesta = idrespuesta;
		this.respuesta = respuesta;
		this.preguntasAdoptante = preguntasAdoptante;
		this.protectora = protectora;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idrespuesta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RespuestasAdoptante other = (RespuestasAdoptante) obj;
		if (idrespuesta != other.idrespuesta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RespuestasAdoptante [idrespuesta=" + idrespuesta + ", respuesta=" + respuesta + ", preguntasAdoptante="
				+ preguntasAdoptante + ", protectora=" + protectora + ", usuario=" + usuario + "]";
	}

}