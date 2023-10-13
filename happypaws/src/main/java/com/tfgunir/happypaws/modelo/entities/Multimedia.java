package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;



/**
 * The persistent class for the multimedias database table.
 * 
 */
@Entity
@Table(name="multimedias")
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

}