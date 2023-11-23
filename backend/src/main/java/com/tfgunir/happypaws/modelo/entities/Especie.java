package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especie database table.
 * 
 */
@Entity
@Table(name="ESPECIE")
@NamedQuery(name="Especie.findAll", query="SELECT e FROM Especie e")
public class Especie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idespecie;

	private String especie;

	//bi-directional many-to-one association to Raza
	//@OneToMany(mappedBy="especie")
	//private List<Raza> razas;

	public Especie() {
	}

	public int getIdespecie() {
		return this.idespecie;
	}

	public void setIdespecie(int idespecie) {
		this.idespecie = idespecie;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}


	public Especie(int idespecie, String especie, List<Raza> razas) {
		this.idespecie = idespecie;
		this.especie = especie;
	}

	@Override
	public String toString() {
		return "Especie [idespecie=" + idespecie + ", especie=" + especie + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idespecie;
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
		Especie other = (Especie) obj;
		if (idespecie != other.idespecie)
			return false;
		return true;
	}
}