package br.com.crud.desafio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.crud.desafio.dao.GenericDao;
import br.com.crud.desafio.dao.ProjetoDao;
import br.com.crud.desafio.entity.Projeto;
import br.com.crud.desafio.entity.Tarefa;
import br.com.crud.desafio.enums.PrioridadeEnum;

@ManagedBean
@ViewScoped
public class TarefaController extends SpringBeanAutowiringSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tarefa tarefa;
	private Projeto projeto;
	private List<Tarefa> tarefas;
	private List<SelectItem> listaProjeto;
	
	@Autowired
	private GenericDao<Tarefa, Integer> tarefaDao;
	
	@Autowired 
	ProjetoDao projetoDao;
	
	public TarefaController() {
		tarefa = new Tarefa();
		projeto = new Projeto();
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
	
	public void editTarefaById(Long id) {
		Tarefa trf = new Tarefa();
		trf.setId(id);
		trf = getTarefa(trf);
	}
	
	public void salvarTarefa() {
		Calendar dataCorrente = Calendar.getInstance();
		
		Tarefa t = new Tarefa();;
	
		t.setTitulo(tarefa.getTitulo());
		t.setDescricao(tarefa.getDescricao());
		t.setEstimativaHoras(tarefa.getEstimativaHoras());
		t.setPrioridade(tarefa.getPrioridade());
		
		
		Projeto prj = new Projeto();
		prj = projetoDao.getById(projeto.getId());
		t.setProjeto(prj);
		
		t.setCreateDate(dataCorrente.getTime());
		tarefaDao.save(t);
	}
	
	public void excluirTarefa(Tarefa tarefa) {
		Tarefa trf = getTarefa(tarefa);
		tarefaDao.delete(trf);
	}
	
	public void excluirTarefaById(Long id) {
		Tarefa trf = new Tarefa();
		trf.setId(id);
		trf = getTarefa(trf);
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
	
	public List<SelectItem> listarProjetos() {
		List<Projeto> projetos  = projetoDao.getAll();
		listaProjeto = new ArrayList();
		for(Projeto projeto : projetos ) {
			SelectItem prj = new SelectItem(projeto.getId(),projeto.getTitulo());
			listaProjeto.add(prj);
		}
		return listaProjeto;
		
	}
	
	public PrioridadeEnum[] listarPrioridades() {
		return PrioridadeEnum.values();
		
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
	
}
