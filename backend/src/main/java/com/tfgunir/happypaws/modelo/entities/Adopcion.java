package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;




/**
 * The persistent class for the adopciones database table.
 * 
 */
@Entity
@Table(name="adopciones")
@NamedQuery(name="Adopcion.findAll", query="SELECT a FROM Adopcion a")
public class Adopcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idadopcion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ADOPCION")
	private Date fechaAdopcion;

	//uni-directional many-to-one association to Animal
	@ManyToOne
	@JoinColumn(name="IDANIMAL")
	private Animal animal;

	//uni-directional many-to-one association to Estadosadopcion
	@ManyToOne
	@JoinColumn(name="IDESTADOADOPCION")
	private Estadosadopcion estadosadopcion;

	//uni-directional many-to-one association to Protectora
	@ManyToOne
	@JoinColumn(name="IDPROTECTORA")
	private Protectora protectora;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public Adopcion() {
	}

	public int getIdadopcion() {
		return this.idadopcion;
	}

	public void setIdadopcion(int idadopcion) {
		this.idadopcion = idadopcion;
	}

	public Date getFechaAdopcion() {
		return this.fechaAdopcion;
	}

	public void setFechaAdopcion(Date fechaAdopcion) {
		this.fechaAdopcion = fechaAdopcion;
	}

	public Animal getAnimal() {
		return this.animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Estadosadopcion getEstadosadopcion() {
		return this.estadosadopcion;
	}

	public void setEstadosadopcion(Estadosadopcion estadosadopcion) {
		this.estadosadopcion = estadosadopcion;
	}

	public Protectora getProtectora() {
		return this.protectora;
	}

	public void setProtectora(Protectora protectora) {
		this.protectora = protectora;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}