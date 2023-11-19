package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the estadosadopciones database table.
 * 
 */
@Entity
@Table(name="ESTADOSADOPCIONES")
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

	public void setEnCurso() {
		this.idestadoadopcion = 1;
		this.estado = "En curso";
	}
	public void getEnCurso() {
		this.idestadoadopcion = 1;
		this.estado = "En curso";
	}

	public void setRealizada() {
		this.idestadoadopcion = 2;
		this.estado = "Realizada";
	}

	public void setRechazada() {
		this.idestadoadopcion = 3;
		this.estado = "Rechazada";
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Estadosadopcion(int idestadoadopcion, String estado) {
		this.idestadoadopcion = idestadoadopcion;
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idestadoadopcion;
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
		Estadosadopcion other = (Estadosadopcion) obj;
		if (idestadoadopcion != other.idestadoadopcion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estadosadopcion [idestadoadopcion=" + idestadoadopcion + ", estado=" + estado + "]";
	}

}