package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the tipos_atributos database table.
 * 
 */
@Entity
@Table(name="TIPOS_ATRIBUTOS")
@NamedQuery(name="TiposAtributo.findAll", query="SELECT t FROM TiposAtributo t")
public class TiposAtributo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtipo;

	private String especie;

	private String tipo;

	public TiposAtributo() {
	}

	public int getIdtipo() {
		return this.idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public TiposAtributo(int idtipo, String especie, String tipo) {
		this.idtipo = idtipo;
		this.especie = especie;
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idtipo;
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
		TiposAtributo other = (TiposAtributo) obj;
		if (idtipo != other.idtipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TiposAtributo [idtipo=" + idtipo + ", especie=" + especie + ", tipo=" + tipo + "]";
	}

}