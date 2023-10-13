package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;



/**
 * The persistent class for the protectoras database table.
 * 
 */
@Entity
@Table(name="protectoras")
@NamedQuery(name="Protectora.findAll", query="SELECT p FROM Protectora p")
public class Protectora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprotectora;

	private String descripcion;

	private String direccion;

	private String nombre;

	@Column(name="URL_LOGO")
	private String urlLogo;

	//uni-directional many-to-one association to Estadosprotectora
	@ManyToOne
	@JoinColumn(name="IDESTADOPROTECTORA")
	private Estadosprotectora estadosprotectora;

	//uni-directional many-to-one association to Municipio
	@ManyToOne
	@JoinColumn(name="IDMUNICIPIO")
	private Municipio municipio;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public Protectora() {
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

}