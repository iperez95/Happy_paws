package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tamaño database table.
 * 
 */
@Entity
@Table(name="TAMANO")
@NamedQuery(name="Tamano.findAll", query="SELECT t FROM Tamano t")
public class Tamano implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idtamano;

	private String tamano;

	//bi-directional many-to-one association to Animale
	//@OneToMany(mappedBy="tamaño")
	//private List<Animal> animales;

	public Tamano() {
	}

	public int getIdtamano() {
		return this.idtamano;
	}

	public void setIdtamano(int idtamano) {
		this.idtamano = idtamano;
	}

	public String getTamano() {
		return this.tamano;
	}

	public void setTamaño(String tamano) {
		this.tamano = tamano;
	}

	public Tamano(int idtamano, String tamano) {
		this.idtamano = idtamano;
		this.tamano = tamano;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idtamano;
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
		Tamano other = (Tamano) obj;
		if (idtamano != other.idtamano)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tamaño [idtamaño=" + idtamano + ", tamaño=" + tamano + "]";
	}
}