package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;




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

}