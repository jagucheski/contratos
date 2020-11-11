package br.com.jagucheski.contratos.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "razao_social")
	@NotEmpty(message = "O Campo Nome é obrigatório")
	private String razaoSocial;

	@NotEmpty(message = "O Campo Nome é obrigatório")
	private String cnpj;

	@NotEmpty(message = "O Campo Nome é obrigatório")
	private String email;

	private String telefone;

	private String endereco;

	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.MERGE)
	private List<Contrato> contratos = new ArrayList<Contrato>();

	public Fornecedor() {
	}

	public Fornecedor(String razaoSocial, String cnpj, String email, String telefone, String endereco) {
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	@Override
	public String toString() {
		return "Fornecedor [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", email=" + email
				+ ", telefone=" + telefone + ", endereco=" + endereco + "]";
	}

}
