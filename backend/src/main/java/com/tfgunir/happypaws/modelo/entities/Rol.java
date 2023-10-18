package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrol;

	private String nombre;

	public Rol() {
	}

	public int getIdrol() {
		return this.idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Rol(int idrol, String nombre) {
		this.idrol = idrol;
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idrol;
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
		Rol other = (Rol) obj;
		if (idrol != other.idrol)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [idrol=" + idrol + ", nombre=" + nombre + "]";
	}

}