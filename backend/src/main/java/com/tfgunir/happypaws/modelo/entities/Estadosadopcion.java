package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the estadosadopciones database table.
 * 
 */
@Entity
@Table(name="estadosadopciones")
@NamedQuery(name="Estadosadopcion.findAll", query="SELECT e FROM Estadosadopcion e")
public class Estadosadopcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idestadoadopcion;

	private String estado;

	public Estadosadopcion() {
	}

	public int getIdestadoadopcion() {
		return this.idestadoadopcion;
	}

	public void setIdestadoadopcion(int idestadoadopcion) {
		this.idestadoadopcion = idestadoadopcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}