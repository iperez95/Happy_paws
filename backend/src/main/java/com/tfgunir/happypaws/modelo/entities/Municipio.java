package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




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

	public Municipio(int idmunicipio, String municipio, Provincia provincia) {
		this.idmunicipio = idmunicipio;
		this.municipio = municipio;
		this.provincia = provincia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idmunicipio;
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
		Municipio other = (Municipio) obj;
		if (idmunicipio != other.idmunicipio)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Municipio [idmunicipio=" + idmunicipio + ", municipio=" + municipio + ", provincia=" + provincia + "]";
	}

}