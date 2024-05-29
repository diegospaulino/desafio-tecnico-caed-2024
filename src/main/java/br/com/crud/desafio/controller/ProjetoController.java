package br.com.crud.desafio.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.com.crud.desafio.dao.ProjetoDao;
import br.com.crud.desafio.dao.TarefaDao;
import br.com.crud.desafio.entity.Projeto;
import br.com.crud.desafio.entity.Tarefa;

@ManagedBean
@ViewScoped
public class ProjetoController extends SpringBeanAutowiringSupport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Projeto projeto;
	private List<Projeto> projetos;
	
	@Autowired
	private ProjetoDao projetoDao;
	
	@Autowired
	private TarefaDao tarefaDao;
	
	public ProjetoController() {
		projeto = new Projeto();
	}
	
	public String cadastrarProjetoIndex() {
		projeto = new Projeto();
		return "cadastrarProjeto";
	}
	public String editProjeto(Projeto projeto) {
		this.projeto = new Projeto();
		this.projeto = projetoDao.getById(projeto.getId());
		return "cadastrarProjeto_edit";
	}
	
	public String salvarProjeto() {
		Calendar dataCorrente = Calendar.getInstance();
		Projeto prj = new Projeto();
		prj.setCreateDate(dataCorrente.getTime());
		prj.setDataInicio(dataCorrente.getTime());
		prj.setTitulo(projeto.getTitulo());
		prj.setDescricao(projeto.getDescricao());
		projetoDao.save(prj);
		
		return "listarProjetos";
	}
	
	public String atualizarProjeto(Projeto projeto) {
		Calendar dataCorrente = Calendar.getInstance();
		Projeto prj = new Projeto();
		prj = projetoDao.getById(projeto.getId());
		prj.setTitulo(projeto.getTitulo());
		prj.setDescricao(projeto.getDescricao());
		prj.setUpdateDate(dataCorrente.getTime());
		projetoDao.update(prj);
		return "listarProjetos";
	}
	
	public String excluirProjeto(Projeto projeto) {
		this.projeto.setId(projeto.getId());
		List<Tarefa> tarefas = new ArrayList<>();
		tarefas = tarefaDao.getAll();
		for(Integer i = 0; i < tarefas.size(); i++) {
			Tarefa tarefa = new Tarefa();
			tarefa = tarefas.get(i);
			if(tarefa.getProjeto().getId().equals(this.projeto.getId())) {
				tarefaDao.delete(tarefa);
			}
		}
		projetoDao.delete(this.projeto);
		
		return "listarProjetos";
	}
	
	public List<Projeto> getProjetos() {
		projetos = new ArrayList<Projeto>();
		projetos.addAll(projetoDao.getAll());
		return projetos;
	}
	
	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}
	
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}
}
