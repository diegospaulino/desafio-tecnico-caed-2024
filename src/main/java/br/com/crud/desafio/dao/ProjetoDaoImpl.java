package br.com.crud.desafio.dao;

import org.springframework.stereotype.Repository;

import br.com.crud.desafio.entity.Projeto;

@Repository("projetoDao")
public class ProjetoDaoImpl extends GenericDaoImpl<Projeto, Integer> implements ProjetoDao{
	
}
