package br.com.crud.desafio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProjetoDaoImpl<Projeto, ID extends Serializable> implements GenericDao<Projeto, ID>{
	
	private EntityManager entityManager;
	private final Class<Projeto> projetoClass;
	
	public Class<Projeto> getObjectClass(){
		return this.projetoClass;
	}
	
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	protected EntityManager getEntityManager() {
		if(entityManager == null) {
			throw new IllegalStateException("Erro Projeto!");
		}
		
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public ProjetoDaoImpl() {
		this.projetoClass = (Class<Projeto>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Projeto update(Projeto projeto) {
		getEntityManager().merge(projeto);
		return projeto;
	}
	
	public void delete(Projeto projeto) {
		projeto = getEntityManager().merge(projeto);
		getEntityManager().remove(projeto);
	}
	
	public Projeto getById(ID id) {
		return (Projeto) getEntityManager().find(projetoClass, id);
	}
	
	public Projeto save(Projeto projeto) {
		getEntityManager().clear();
		getEntityManager().persist(projeto);
		return projeto;
	}
	
	@SuppressWarnings("unchecked")
	public List<Projeto> getAll() {
		String query = "SELECT * FROM PROJETO;";
		Query q = getEntityManager().createQuery(query);
		return q.getResultList();
	}
}
