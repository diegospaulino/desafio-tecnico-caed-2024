package br.com.crud.desafio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{
	
	private EntityManager entityManager;
	private final Class<T> objectClass;
	
	public Class<T> getObjectClass(){
		return this.objectClass;
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
	public GenericDaoImpl() {
		this.objectClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public T update(T object) {
		getEntityManager().merge(object);
		return object;
	}
	
	public void delete(T object) {
		object = getEntityManager().merge(object);
		getEntityManager().remove(object);
	}
	
	public T getById(ID id) {
		return (T) getEntityManager().find(objectClass, id);
	}
	
	public T save(T object) {
		getEntityManager().clear();
		getEntityManager().persist(object);
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String query = "SELECT * FROM " + objectClass.getSimpleName() + ";";
		Query q = getEntityManager().createQuery(query);
		return q.getResultList();
	}
}
