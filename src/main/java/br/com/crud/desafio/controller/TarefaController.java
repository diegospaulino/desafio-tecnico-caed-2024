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
	private List<Tarefa> tarefas;
	private List<SelectItem> listaProjeto;
	
	@Autowired
	private GenericDao<Tarefa, Integer> tarefaDao;
	
	@Autowired 
	ProjetoDao projetoDao;
	
	public TarefaController() {
		tarefa = new Tarefa();
		Projeto projeto = new Projeto();
		tarefa.setProjeto(projeto);
	}
	
	public String formTarefa() {
		this.tarefa = new Tarefa();
		return "formTarefa";
	}
	
	public Tarefa getTarefa(Tarefa tarefa) {
		Tarefa trf = tarefaDao.getById(tarefa.getId());
		return trf;
	}
	
	public String cadastrarTarefaIndex() {
		this.tarefa = new Tarefa();
		Projeto projeto = new Projeto();
		tarefa.setProjeto(projeto);
		
		return "cadastrarTarefa";
	}
	
	public String editTarefa(Tarefa tarefa) {
		this.tarefa = new Tarefa();
		this.tarefa = tarefaDao.getById(tarefa.getId());
		return "cadastrarTarefa_edit";
	}
	
	public void editTarefaById(Long id) {
		Tarefa trf = new Tarefa();
		trf.setId(id);
		trf = getTarefa(trf);
	}
	
	public String atualizarTarefa(Tarefa tarefa) {
		Calendar dataCorrente = Calendar.getInstance();
		Tarefa trf = new Tarefa();
		
		trf = tarefaDao.getById(tarefa.getId());
		trf.setTitulo(tarefa.getTitulo());
		trf.setDescricao(tarefa.getDescricao());
		trf.setEstimativaHoras(tarefa.getEstimativaHoras());
		trf.setPrioridade(tarefa.getPrioridade());
		trf.setCreateDate(dataCorrente.getTime());
		
		Projeto prj = new Projeto();
		if(!tarefa.getProjeto().equals(null) && !tarefa.getProjeto().getId().equals(null)) {
			prj = projetoDao.getById(tarefa.getProjeto().getId());
		}
		trf.setProjeto(prj);
		
		tarefaDao.update(trf);
		return "listarTarefas";
	}
	
	public String salvarTarefa() {
		Calendar dataCorrente = Calendar.getInstance();
		
		Tarefa trf = new Tarefa();;
	
		trf.setTitulo(tarefa.getTitulo());
		trf.setDescricao(tarefa.getDescricao());
		trf.setEstimativaHoras(tarefa.getEstimativaHoras());
		trf.setPrioridade(tarefa.getPrioridade());
		
		
		Projeto prj = new Projeto();
		if(!tarefa.getProjeto().equals(null) && !tarefa.getProjeto().getId().equals(null)) {
			prj = projetoDao.getById(tarefa.getProjeto().getId());
		}
		trf.setProjeto(prj);
		
		trf.setCreateDate(dataCorrente.getTime());
		tarefaDao.save(trf);
		return "listarTarefas";
	}
	
	public String excluirTarefa(Tarefa tarefa) {
		Tarefa trf = getTarefa(tarefa);
		tarefaDao.delete(trf);
		return "listarTarefas";
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
}
