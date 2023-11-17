package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;




/**
 * The persistent class for the multimedias database table.
 * 
 */
@Entity
@Table(name="MULTIMEDIAS")
@NamedQuery(name="Multimedia.findAll", query="SELECT m FROM Multimedia m")
public class Multimedia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmultimedia;

	@Lob
	private String enlace;

	//uni-directional many-to-one association to Animal
	@ManyToOne
	@JoinColumn(name="IDANIMAL")
	private Animal animal;

	public Multimedia() {
	}

	public int getIdmultimedia() {
		return this.idmultimedia;
	}

	public void setIdmultimedia(int idmultimedia) {
		this.idmultimedia = idmultimedia;
	}

	public String getEnlace() {
		return this.enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Animal getAnimal() {
		return this.animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Multimedia(int idmultimedia, String enlace, Animal animal) {
		this.idmultimedia = idmultimedia;
		this.enlace = enlace;
		this.animal = animal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idmultimedia;
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
		Multimedia other = (Multimedia) obj;
		if (idmultimedia != other.idmultimedia)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Multimedia [idmultimedia=" + idmultimedia + ", enlace=" + enlace + ", animal=" + animal + "]";
	}

}