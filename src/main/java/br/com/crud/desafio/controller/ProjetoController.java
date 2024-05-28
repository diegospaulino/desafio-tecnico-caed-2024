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
import br.com.crud.desafio.entity.Projeto;

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
	private GenericDao<Projeto, Integer> projetoDao;
	
	public ProjetoController() {
		projeto = new Projeto();
	}

	public ProjetoController(Projeto projeto, GenericDao<Projeto, Integer> projetoDao) {
		super();
		this.projeto = projeto;
		this.projetoDao = projetoDao;
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
		
		return "listaProjetos";
	}
	
	public String atualizarProjeto(Projeto projeto) {
		Calendar dataCorrente = Calendar.getInstance();
		this.projeto.setTitulo(projeto.getTitulo());
		this.projeto.setDescricao(projeto.getDescricao());
		this.projeto.setCreateDate(projeto.getCreateDate());
		this.projeto.setDataInicio(projeto.getDataInicio());
		this.projeto.setTarefas(projeto.getTarefas());
		this.projeto.setUpdateDate(dataCorrente.getTime());
		projetoDao.update(this.projeto);
		
		return "listaProjetos";
	}
	
	public String excluirProjeto(Projeto projeto) {
		this.projeto.setId(projeto.getId());
		projetoDao.delete(this.projeto);
		
		return "listaProjetos";
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
