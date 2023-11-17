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
@Table(name="ANIMALES")
@NamedQuery(name="Animal.findAll", query="SELECT a FROM Animal a")
public class Animal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idanimal;

	private byte enabled;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_enabled")
	private Date fechaenabled;

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

	public byte getenabled() {
		return this.enabled;
	}

	public void setenabled(byte enabled) {
		this.enabled = enabled;
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

	public Date getFechaenabled() {
		return this.fechaenabled;
	}

	public void setFechaenabled(Date fechaenabled) {
		this.fechaenabled = fechaenabled;
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

	public Animal(int idanimal, byte enabled, String descripcion, Date fechaAlta, Date fechaenabled,
			Date fechaNacimiento, String nombre, Municipio municipio, Protectora protectora,
			List<ValoresAtributo> valoresAtributo) {
		this.idanimal = idanimal;
		this.enabled = enabled;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaenabled = fechaenabled;
		this.fechaNacimiento = fechaNacimiento;
		this.nombre = nombre;
		this.municipio = municipio;
		this.protectora = protectora;
		this.valoresAtributo = valoresAtributo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idanimal;
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
		Animal other = (Animal) obj;
		if (idanimal != other.idanimal)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Animal [idanimal=" + idanimal + ", enabled=" + enabled + ", descripcion=" + descripcion + ", fechaAlta="
				+ fechaAlta + ", fechaenabled=" + fechaenabled + ", fechaNacimiento=" + fechaNacimiento + ", nombre="
				+ nombre + ", municipio=" + municipio + ", protectora=" + protectora + ", valoresAtributo="
				+ valoresAtributo + "]";
	}

}