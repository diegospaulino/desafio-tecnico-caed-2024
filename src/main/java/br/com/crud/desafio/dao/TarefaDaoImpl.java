package br.com.crud.desafio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TarefaDaoImpl<Tarefa, ID extends Serializable> implements GenericDao<Tarefa, ID>{
	
	private EntityManager entityManager;
	private final Class<Tarefa> tarefaClass;
	
	public Class<Tarefa> getObjectClass(){
		return this.tarefaClass;
	}
	
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	protected EntityManager getEntityManager() {
		if(entityManager == null) {
			throw new IllegalStateException("Erro Tarefa!");
		}
		
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public TarefaDaoImpl() {
		this.tarefaClass = (Class<Tarefa>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public Tarefa update(Tarefa tarefa) {
		getEntityManager().merge(tarefa);
		return tarefa;
	}
	
	public void delete(Tarefa tarefa) {
		tarefa = getEntityManager().merge(tarefa);
		getEntityManager().remove(tarefa);
	}
	
	public Tarefa getById(ID id) {
		return (Tarefa) getEntityManager().find(tarefaClass, id);
	}
	
	public Tarefa save(Tarefa tarefa) {
		getEntityManager().clear();
		getEntityManager().persist(tarefa);
		return tarefa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tarefa> getAll() {
		String query = "SELECT * FROM TAREFA;";
		Query q = getEntityManager().createQuery(query);
		return q.getResultList();
	}
}
