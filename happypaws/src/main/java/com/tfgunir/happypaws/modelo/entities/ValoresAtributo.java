package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import jakarta.persistence.*;


import java.util.List;


/**
 * The persistent class for the valores_atributos database table.
 * 
 */
@Entity
@Table(name="valores_atributos")
@NamedQuery(name="ValoresAtributo.findAll", query="SELECT v FROM ValoresAtributo v")
public class ValoresAtributo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idvalor;

	private String valor;

	//bi-directional many-to-many association to Animal
	@ManyToMany
	@JoinTable(
		name="atributos_animales"
		, joinColumns={
			@JoinColumn(name="IDVALOR")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDANIMAL")
			}
		)
	private List<Animal> animal;

	//uni-directional many-to-one association to TiposAtributo
	@ManyToOne
	@JoinColumn(name="IDTIPO")
	private TiposAtributo tiposAtributo;

	public ValoresAtributo() {
	}

	public int getIdvalor() {
		return this.idvalor;
	}

	public void setIdvalor(int idvalor) {
		this.idvalor = idvalor;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<Animal> getAnimal() {
		return this.animal;
	}

	public void setAnimal(List<Animal> animal) {
		this.animal = animal;
	}

	public TiposAtributo getTiposAtributo() {
		return this.tiposAtributo;
	}

	public void setTiposAtributo(TiposAtributo tiposAtributo) {
		this.tiposAtributo = tiposAtributo;
	}

}