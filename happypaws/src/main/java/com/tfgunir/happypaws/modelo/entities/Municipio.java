package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;



/**
 * The persistent class for the municipios database table.
 * 
 */
@Entity
@Table(name="municipios")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmunicipio;

	private int codmunicipio;

	private int dc;

	private String municipio;

	//uni-directional many-to-one association to Provincia
	@ManyToOne
	@JoinColumn(name="IDPROVINCIA")
	private Provincia provincia;

	public Municipio() {
	}

	public int getIdmunicipio() {
		return this.idmunicipio;
	}

	public void setIdmunicipio(int idmunicipio) {
		this.idmunicipio = idmunicipio;
	}

	public int getCodmunicipio() {
		return this.codmunicipio;
	}

	public void setCodmunicipio(int codmunicipio) {
		this.codmunicipio = codmunicipio;
	}

	public int getDc() {
		return this.dc;
	}

	public void setDc(int dc) {
		this.dc = dc;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}