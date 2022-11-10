package model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PIZZA")
public class Pizza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private long id;
	
	@Column(name="nome")
	private String nome;
	
	
	@ManyToOne
	@JoinColumn(name="id_utente", nullable=false)
	private Utente utente;
	
	@ManyToOne
	@JoinColumn(name="id_impasto", nullable=false)
	private Impasto impasto;


	@ManyToMany(targetEntity = Ingrediente.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "PIZZA_INGREDIENTE", 
				joinColumns = { @JoinColumn(name = "id_pizza") }, 
				inverseJoinColumns = { @JoinColumn(name = "id_ingrediente") })
	private Set<Ingrediente> ingrediente;
	
	public Pizza() {}
	
	public Pizza(String nome, Utente utente, Impasto impasto) {
		super();
		this.id = id;
		this.nome = nome;
		this.utente = utente;
		this.impasto = impasto;
	}

	
	public Set<Ingrediente> getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Set<Ingrediente> ingrediente) {
		this.ingrediente = ingrediente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Impasto getImpasto() {
		return impasto;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}
	
	
}
