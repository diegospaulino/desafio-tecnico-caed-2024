package br.com.crud.desafio.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{
	
	private EntityManager entityManager;
	private final Class<T> objectClass;
	
	public Class<T> getObjectClass(){
		return this.objectClass;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
	}
	
	protected EntityManager getEntityManager() {
		if(entityManager == null) {
			throw new IllegalStateException("Erro em " + objectClass.getSimpleName() + "!");
		}
		
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.objectClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T update(T object) {
		entityManager.merge(object);
		return object;
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void delete(T object) {
		object = entityManager.merge(object);
		entityManager.remove(object);
	}
	
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public T save(T object) {
		entityManager.clear();
		entityManager.persist(object);
		entityManager.close();
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		String hql = "FROM " + objectClass.getSimpleName();
		Query q = entityManager.createQuery(hql);
		return q.getResultList();
	}

	@Override
	public T getById(Long id) {
		return (T) entityManager.find(objectClass, id);
	}
}
