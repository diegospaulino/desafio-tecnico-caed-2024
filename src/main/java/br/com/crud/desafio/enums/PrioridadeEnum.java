package br.com.crud.desafio.enums;

public enum PrioridadeEnum {
	MUITO_ALTA(0),
	ALTA(1),
	BAIXA(2),
	MUITO_BAIXA(3);
	
	private Integer prioridade;
	
	private PrioridadeEnum(Integer prioridade) {
		this.prioridade = prioridade;
	}
	
	private Integer getPrioridade() {
		return prioridade;
	}
	
}
