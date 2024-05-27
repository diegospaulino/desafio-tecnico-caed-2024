package br.com.crud.desafio.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.crud.desafio.dao.GenericDao;
import br.com.crud.desafio.entity.Tarefa;

@Controller("tarefaController")
@Scope("session")
public class TarefaController {
	
	private Tarefa tarefa;
	
	@Resource
	private GenericDao<Tarefa, Integer> tarefaDao;

	public TarefaController(Tarefa tarefa, GenericDao<Tarefa, Integer> tarefaDao) {
		super();
		this.tarefa = tarefa;
		this.tarefaDao = tarefaDao;
	}
	
	public String formTarefa() {
		this.tarefa = new Tarefa();
		return "formTarefa";
	}
	
	public List<Tarefa> listaTodasTarefas() {
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefaDao.getAll();
		return tarefas;
	}
	
	public Tarefa getTarefa(Integer id) {
		Tarefa trf = tarefaDao.getById(id);
		return trf;
	}
	
	public String editTarefa(Integer id) {
		tarefa = getTarefa(id);
		return "formTarefa";
	}
	
	public String salvarTarefa() {
		Calendar dataCorrente = Calendar.getInstance();
		if(tarefa.getId() == null) {
			tarefa.setCreateDate(dataCorrente.getTime());
			tarefaDao.save(tarefa);
		}
		else {
			tarefa.setUpdateDate(dataCorrente.getTime());
			tarefaDao.update(tarefa);
		}
		
		return "sucesso";
	}
	
	public String excluirTarefa(Integer id) {
		Tarefa tarefa = getTarefa(id);
		tarefaDao.delete(tarefa);
		
		return "listaTarefas";
	}
}
