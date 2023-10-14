package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the animales database table.
 * 
 */
@Entity
@Table(name="animales")
@NamedQuery(name="Animal.findAll", query="SELECT a FROM Animal a")
public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idanimal;

	private byte baja;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_BAJA")
	private Date fechaBaja;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	private String nombre;

	//uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="IDMUNICIPIO")
	private Municipio municipio;

	//uni-directional many-to-one association to Protectora
	@ManyToOne
	@JoinColumn(name="IDPROTECTORA")
	private Protectora protectora;

	//bi-directional many-to-many association to ValoresAtributo
	@ManyToMany(mappedBy="animal")
	private List<ValoresAtributo> valoresAtributo;

	public Animal() {
	}

	public int getIdanimal() {
		return this.idanimal;
	}

	public void setIdanimal(int idanimal) {
		this.idanimal = idanimal;
	}

	public byte getBaja() {
		return this.baja;
	}

	public void setBaja(byte baja) {
		this.baja = baja;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Protectora getProtectora() {
		return this.protectora;
	}

	public void setProtectora(Protectora protectora) {
		this.protectora = protectora;
	}

	public List<ValoresAtributo> getValoresAtributo() {
		return this.valoresAtributo;
	}

	public void setValoresAtributo(List<ValoresAtributo> valoresAtributo) {
		this.valoresAtributo = valoresAtributo;
	}

}