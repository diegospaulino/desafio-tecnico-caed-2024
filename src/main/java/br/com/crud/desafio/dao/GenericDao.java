package br.com.crud.desafio.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

	public Class<T> getObjectClass();
	public T save(T object);
	public T getById(Long ID);
	public T update(T object);
	public void delete(T object);
	public List<T> getAll();
}
