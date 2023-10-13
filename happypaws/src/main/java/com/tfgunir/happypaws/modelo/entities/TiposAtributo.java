package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;



/**
 * The persistent class for the tipos_atributos database table.
 * 
 */
@Entity
@Table(name="tipos_atributos")
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

}