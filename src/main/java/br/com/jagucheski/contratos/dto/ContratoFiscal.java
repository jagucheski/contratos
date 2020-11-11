package br.com.jagucheski.contratos.dto;

public interface ContratoFiscal {

	
	/**
	 * Para uso de DTO com Spring Data as funcoes get devem ter o mesmo nome da coluna do SQL.
	 * Ex.:  'getNumero' no DTO,  'c.numero'         no SQL;
	 * 		 'getFiscal' no DTO,  'f.nome as fiscal' no SQL;
	 * */
	
	Long getId();
	String getNumero();
	Double getValor();
	String getObjeto();
	String getFiscal();
}
