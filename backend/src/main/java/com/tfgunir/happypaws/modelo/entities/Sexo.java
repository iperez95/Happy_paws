package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the sexo database table.
 * 
 */
@Entity
@Table(name="SEXO")
@NamedQuery(name="Sexo.findAll", query="SELECT s FROM Sexo s")
public class Sexo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idsexo;

	private String sexo;

	public Sexo() {
	}

	public int getIdsexo() {
		return this.idsexo;
	}

	public void setIdsexo(int idsexo) {
		this.idsexo = idsexo;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Sexo(int idsexo, String sexo) {
		this.idsexo = idsexo;
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idsexo;
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
		Sexo other = (Sexo) obj;
		if (idsexo != other.idsexo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sexo [idsexo=" + idsexo + ", sexo=" + sexo + "]";
	}
}