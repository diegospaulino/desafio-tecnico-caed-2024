package br.com.crud.desafio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.crud.desafio.enums.PrioridadeEnum;


@Entity
@Table(name = "Tarefa")
public class Tarefa extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5131878205694939108L;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@Column
	private PrioridadeEnum prioridade;
	
	@Column(name="estimativa_horas")
	private Integer estimavaHoras;
	
	@ManyToOne
	private Projeto projeto;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PrioridadeEnum getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(PrioridadeEnum prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getEstimavaHoras() {
		return estimavaHoras;
	}

	public void setEstimavaHoras(Integer estimavaHoras) {
		this.estimavaHoras = estimavaHoras;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
	

}
