package br.com.jagucheski.contratos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;


@Entity
public class Fiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Campo Nome é obrigatório")
	private String nome;

	@NotEmpty(message = "O Campo Matrícula é obrigatório")
	private String matricula;

	@NotEmpty(message = "O Campo E-mail é obrigatório")
	private String email;

	@OneToMany(mappedBy = "fiscal", cascade = CascadeType.MERGE)
	private List<Contrato> contratos = new ArrayList<Contrato>();

	public Fiscal() {
	}

	public Fiscal(String nome, String matricula, String email) {
		this.nome = nome;
		this.matricula = matricula;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	@Override
	public String toString() {
		return "Fiscal [id=" + id + ", nome=" + nome + ", matricula=" + matricula + ", email=" + email + "]";
	}

}
