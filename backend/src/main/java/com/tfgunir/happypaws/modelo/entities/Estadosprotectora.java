package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the estadosprotectoras database table.
 * 
 */
@Entity
@Table(name="estadosprotectoras")
@NamedQuery(name="Estadosprotectora.findAll", query="SELECT e FROM Estadosprotectora e")
public class Estadosprotectora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idestadoprotectora;

	private String estado;

	public Estadosprotectora() {
	}

	public int getIdestadoprotectora() {
		return this.idestadoprotectora;
	}

	public void setIdestadoprotectora(int idestadoprotectora) {
		this.idestadoprotectora = idestadoprotectora;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Estadosprotectora(int idestadoprotectora, String estado) {
		this.idestadoprotectora = idestadoprotectora;
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idestadoprotectora;
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
		Estadosprotectora other = (Estadosprotectora) obj;
		if (idestadoprotectora != other.idestadoprotectora)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estadosprotectora [idestadoprotectora=" + idestadoprotectora + ", estado=" + estado + "]";
	}

}