package br.com.crud.desafio.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_projeto")
public class Projeto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 851995967775950283L;
	
	@Column
	private String titulo;
	
	@Column
	private String descricao;
	
	@Column
	private Date dataInicio;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projeto")
	private List<Tarefa> tarefas;

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

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setPrioridade(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	

}
