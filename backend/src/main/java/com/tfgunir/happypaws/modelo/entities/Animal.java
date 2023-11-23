package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idanimal;

	private String descripcion;

	private boolean enabled;

	private boolean envio;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	@Temporal(TemporalType.DATE)
	private Date FECHA_enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;

	//uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="IDMUNICIPIO")
	private Municipio municipio;

	//uni-directional many-to-one association to Protectora
	@ManyToOne
	@JoinColumn(name="IDPROTECTORA")
	private Protectora protectora;

	private String nombre;

	//bi-directional many-to-one association to Raza
	@ManyToOne
	@JoinColumn(name="IDRAZA")
	private Raza raza;

	//bi-directional many-to-one association to Sexo
	@ManyToOne
	@JoinColumn(name="IDSEXO")
	private Sexo sexo;

	//bi-directional many-to-one association to Tamaño
	@ManyToOne
	@JoinColumn(name="IDTAMANO")
	private Tamano tamano;

	public Animal() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getIdanimal() {
		return idanimal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public boolean getEnvio() {
		return envio;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public Date getFECHA_enabled() {
		return FECHA_enabled;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public Protectora getProtectora() {
		return protectora;
	}

	public String getNombre() {
		return nombre;
	}

	public Raza getRaza() {
		return raza;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public Tamano getTamano() {
		return tamano;
	}

	public void setIdanimal(int idanimal) {
		this.idanimal = idanimal;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setEnvio(boolean envio) {
		this.envio = envio;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public void setFECHA_enabled(Date fECHA_enabled) {
		FECHA_enabled = fECHA_enabled;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public void setProtectora(Protectora protectora) {
		this.protectora = protectora;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setTamano(Tamano tamano) {
		this.tamano = tamano;
	}

	public Animal(int idanimal, String descripcion, boolean enabled, boolean envio, Date fechaAlta, Date fECHA_enabled,
			Date fechaNacimiento, Municipio municipio, Protectora protectora, String nombre, Raza raza, Sexo sexo,
			Tamano tamano) {
		this.idanimal = idanimal;
		this.descripcion = descripcion;
		this.enabled = enabled;
		this.envio = envio;
		this.fechaAlta = fechaAlta;
		this.FECHA_enabled = fECHA_enabled;
		this.fechaNacimiento = fechaNacimiento;
		this.municipio = municipio;
		this.protectora = protectora;
		this.nombre = nombre;
		this.raza = raza;
		this.sexo = sexo;
		this.tamano = tamano;
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
		return "Animal [idanimal=" + idanimal + ", descripcion=" + descripcion + ", enabled=" + enabled + ", envio="
				+ envio + ", fechaAlta=" + fechaAlta + ", FECHA_enabled=" + FECHA_enabled + ", fechaNacimiento="
				+ fechaNacimiento + ", municipio=" + municipio + ", protectora=" + protectora + ", nombre=" + nombre
				+ ", raza=" + raza + ", sexo=" + sexo + ", tamaño=" + tamano + "]";
	}

	
}