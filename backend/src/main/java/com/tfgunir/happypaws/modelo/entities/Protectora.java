package com.tfgunir.happypaws.modelo.entities;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the protectoras database table.
 * 
 */
@Entity
@Table(name = "PROTECTORAS")
@NamedQuery(name = "Protectora.findAll", query = "SELECT p FROM Protectora p")
public class Protectora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idprotectora;

	private String descripcion;

	private String direccion;

	private String nombre;

	private String email;

	private String telefono;

	

	@Column(name = "URL_LOGO")
	private String urlLogo;

	// uni-directional many-to-one association to Estadosprotectora
	@ManyToOne
	@JoinColumn(name = "IDESTADOPROTECTORA")
	@JsonIgnore
	private Estadosprotectora estadosprotectora;

	// uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name = "IDMUNICIPIO")
	@JsonIgnore
	private Municipio municipio;

	// uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "IDUSUARIO")
	@JsonIgnore
	private Usuario usuario;

	public Protectora() {
	}

	public Protectora(int idprotectora, String descripcion, String direccion, String nombre, String email, String telefono,
			String urlLogo, Estadosprotectora estadosprotectora, Municipio municipio, Usuario usuario) {
		this.idprotectora = idprotectora;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.urlLogo = urlLogo;
		this.estadosprotectora = estadosprotectora;
		this.municipio = municipio;
		this.usuario = usuario;
	}

	public int getIdprotectora() {
		return this.idprotectora;
	}

	public void setIdprotectora(int idprotectora) {
		this.idprotectora = idprotectora;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlLogo() {
		return this.urlLogo;
	}

	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Estadosprotectora getEstadosprotectora() {
		return this.estadosprotectora;
	}

	public void setEstadosprotectora(Estadosprotectora estadosprotectora) {
		this.estadosprotectora = estadosprotectora;
	}

	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idprotectora;
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
		Protectora other = (Protectora) obj;
		if (idprotectora != other.idprotectora)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Protectora [idprotectora=" + idprotectora + ", descripcion=" + descripcion + ", direccion=" + direccion
				+ ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", urlLogo=" + urlLogo
				+ ", estadosprotectora=" + estadosprotectora + ", municipio=" + municipio + ", usuario=" + usuario
				+ "]";
	}

	

}