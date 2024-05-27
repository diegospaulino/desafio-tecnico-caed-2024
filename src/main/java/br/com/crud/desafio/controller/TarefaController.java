package br.com.crud.desafio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.crud.desafio.dao.GenericDao;
import br.com.crud.desafio.entity.Tarefa;

@ManagedBean
@ViewScoped
public class TarefaController extends SpringBeanAutowiringSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tarefa tarefa;
	private List<Tarefa> tarefas;
	
	@Autowired
	private GenericDao<Tarefa, Integer> tarefaDao;
	
	public TarefaController() {
		tarefa = new Tarefa();
	}

	public TarefaController(Tarefa tarefa, GenericDao<Tarefa, Integer> tarefaDao) {
		super();
		this.tarefa = tarefa;
		this.tarefaDao = tarefaDao;
	}
	
	public String formTarefa() {
		this.tarefa = new Tarefa();
		return "formTarefa";
	}
	
	public Tarefa getTarefa(Tarefa tarefa) {
		Tarefa trf = tarefaDao.getById(tarefa.getId());
		return trf;
	}
	
	public void editTarefa(Tarefa tarefa) {
		Tarefa trf = new Tarefa();
		trf = getTarefa(tarefa);
	}
	
	public void salvarTarefa() {
		Calendar dataCorrente = Calendar.getInstance();
		if(tarefa.getId() == null) {
			tarefa.setCreateDate(dataCorrente.getTime());
			tarefaDao.save(tarefa);
		}
		else {
			tarefa.setUpdateDate(dataCorrente.getTime());
			tarefaDao.update(tarefa);
		}
	}
	
	public void excluirTarefa(Tarefa tarefa) {
		Tarefa trf = getTarefa(tarefa);
		tarefaDao.delete(trf);
	}
	
	public List<Tarefa> getTarefas() {
		tarefas = new ArrayList<>();
		tarefas.addAll(tarefaDao.getAll());
		return tarefas;
	}
	
	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	public Tarefa getTarefa() {
		return tarefa;
	}
	
	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
}
