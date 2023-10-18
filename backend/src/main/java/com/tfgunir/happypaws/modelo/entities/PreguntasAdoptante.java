package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the preguntas_adoptantes database table.
 * 
 */
@Entity
@Table(name="preguntas_adoptantes")
@NamedQuery(name="PreguntasAdoptante.findAll", query="SELECT p FROM PreguntasAdoptante p")
public class PreguntasAdoptante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpregunta;

	private String pregunta;

	public PreguntasAdoptante() {
	}

	public int getIdpregunta() {
		return this.idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public PreguntasAdoptante(int idpregunta, String pregunta) {
		this.idpregunta = idpregunta;
		this.pregunta = pregunta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idpregunta;
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
		PreguntasAdoptante other = (PreguntasAdoptante) obj;
		if (idpregunta != other.idpregunta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PreguntasAdoptante [idpregunta=" + idpregunta + ", pregunta=" + pregunta + "]";
	}

}