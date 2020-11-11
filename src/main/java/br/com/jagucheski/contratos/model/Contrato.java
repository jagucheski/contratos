
package br.com.jagucheski.contratos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O Campo Objeto é obrigatório")
	private String objeto;

	@NotEmpty(message = "O Campo Número é obrigatório")
	private String numero;

	@NotNull(message = "O Campo Data de Homolocação é obrigatório")
	@Column(name = "data_homologacao")
	private LocalDate dataHomologacao;

	@NotNull(message = "O Campo Data de Encerramento é obrigatório")
	@Column(name = "data_encerramento")
	private LocalDate dataEncerramento;

	@NotNull(message = "O Campo Valor é obrigatório")
	private Double valor = 0d;

	@ManyToOne(fetch = FetchType.LAZY)
	private Fiscal fiscal;

	@ManyToOne(fetch = FetchType.LAZY)
	private Fornecedor fornecedor;

	@Transient
	private String fiscalNome = "";

	@Transient
	private String fornecedorRazao = "";

	public Contrato() {
	}

	public Contrato(String objeto, String numero, LocalDate dataHomologacao, LocalDate dataEncerramento, Double valor,
			Fiscal fiscal, Fornecedor fornecedor) {
		this.objeto = objeto;
		this.numero = numero;
		this.dataHomologacao = dataHomologacao;
		this.dataEncerramento = dataEncerramento;
		this.valor = valor;
		this.fiscal = fiscal;
		this.fornecedor = fornecedor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public LocalDate getDataHomologacao() {
		return dataHomologacao;
	}

	public void setDataHomologacao(LocalDate dataHomologacao) {
		this.dataHomologacao = dataHomologacao;
	}

	public LocalDate getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(LocalDate dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Fiscal getFiscal() {
		return fiscal;
	}

	public void setFiscal(Fiscal fiscal) {
		this.fiscal = fiscal;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getFiscalNome() {
		return fiscalNome;
	}

	public void setFiscalNome(String fiscalNome) {
		this.fiscalNome = fiscalNome;
	}

	public String getFornecedorRazao() {
		return fornecedorRazao;
	}

	public void setFornecedorRazao(String fornecedorRazao) {
		this.fornecedorRazao = fornecedorRazao;
	}

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", objeto=" + objeto + ", numero=" + numero + ", dataHomologacao="
				+ dataHomologacao + ", dataEncerramento=" + dataEncerramento + ", valor=" + valor + ", fiscal=" + fiscal
				+ ", fornecedor=" + fornecedor + ", fiscalNome=" + fiscalNome + ", fornecedorRazao=" + fornecedorRazao
				+ "]";
	}

	public void garregaCampos() {
		this.fornecedorRazao = this.fornecedor.getRazaoSocial();
		this.fiscalNome = this.fiscal.getNome();		
	}

}
