package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tamaño database table.
 * 
 */
@Entity
@Table(name="TAMAÑO")
@NamedQuery(name="Tamaño.findAll", query="SELECT t FROM Tamaño t")
public class Tamaño implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idtamaño;

	private String tamaño;

	//bi-directional many-to-one association to Animale
	//@OneToMany(mappedBy="tamaño")
	//private List<Animal> animales;

	public Tamaño() {
	}

	public int getIdtamaño() {
		return this.idtamaño;
	}

	public void setIdtamaño(int idtamaño) {
		this.idtamaño = idtamaño;
	}

	public String getTamaño() {
		return this.tamaño;
	}

	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}

	public Tamaño(int idtamaño, String tamaño) {
		this.idtamaño = idtamaño;
		this.tamaño = tamaño;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idtamaño;
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
		Tamaño other = (Tamaño) obj;
		if (idtamaño != other.idtamaño)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tamaño [idtamaño=" + idtamaño + ", tamaño=" + tamaño + "]";
	}
}