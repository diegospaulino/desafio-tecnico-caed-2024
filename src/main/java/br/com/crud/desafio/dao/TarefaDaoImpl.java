package br.com.crud.desafio.dao;

import org.springframework.stereotype.Repository;

import br.com.crud.desafio.entity.Tarefa;

@Repository("tarefaDao")
public class TarefaDaoImpl extends GenericDaoImpl<Tarefa, Integer> implements TarefaDao{
	
}
