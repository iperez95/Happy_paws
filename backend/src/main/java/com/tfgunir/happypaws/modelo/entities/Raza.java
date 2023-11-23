package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the raza database table.
 * 
 */
@Entity
@Table(name="RAZA")
@NamedQuery(name="Raza.findAll", query="SELECT r FROM Raza r")
public class Raza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idraza;

	private String raza;

	//bi-directional many-to-one association to Especie
	@ManyToOne
	@JoinColumn(name="IDESPECIE")
	private Especie especie;

	public Raza() {
	}

	public int getIdraza() {
		return this.idraza;
	}

	public void setIdraza(int idraza) {
		this.idraza = idraza;
	}

	public String getRaza() {
		return this.raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public Especie getEspecie() {
		return this.especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public Raza(int idraza, String raza, Especie especie) {
		this.idraza = idraza;
		this.raza = raza;
		this.especie = especie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idraza;
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
		Raza other = (Raza) obj;
		if (idraza != other.idraza)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Raza [idraza=" + idraza + ", raza=" + raza + ", especie=" + especie + "]";
	}
}