package com.tfgunir.happypaws.modelo.entities;


import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the valores_atributos database table.
 * 
 */
@Entity
@Table(name="VALORES_ATRIBUTOS")
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

	public ValoresAtributo(int idvalor, String valor, List<Animal> animal, TiposAtributo tiposAtributo) {
		this.idvalor = idvalor;
		this.valor = valor;
		this.animal = animal;
		this.tiposAtributo = tiposAtributo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idvalor;
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
		ValoresAtributo other = (ValoresAtributo) obj;
		if (idvalor != other.idvalor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValoresAtributo [idvalor=" + idvalor + ", valor=" + valor + ", animal=" + animal + ", tiposAtributo="
				+ tiposAtributo + "]";
	}

}