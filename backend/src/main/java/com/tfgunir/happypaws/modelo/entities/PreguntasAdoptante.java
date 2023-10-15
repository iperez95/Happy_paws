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

}