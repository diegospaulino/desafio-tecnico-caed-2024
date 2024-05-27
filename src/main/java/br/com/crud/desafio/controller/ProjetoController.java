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
	
	public String formProjeto() {
		this.projeto = new Projeto();
		return "formProjeto";
	}
	
	public Projeto getProjeto(Projeto projeto) {
		Projeto prj = projetoDao.getById(projeto.getId());
		return prj;
	}
	
	public void editProjeto(Projeto projeto) {
		Projeto prj = new Projeto(); 
		prj = getProjeto(projeto);
	}
	
	public void salvarProjeto() {
		Calendar dataCorrente = Calendar.getInstance();
		Projeto prj = new Projeto();
		prj.setCreateDate(dataCorrente.getTime());
		prj.setDataInicio(dataCorrente.getTime());
		prj.setTitulo(projeto.getTitulo());
		prj.setDescricao(projeto.getDescricao());
		projetoDao.save(prj);
	}
	
	public void atualizarProjeto(Projeto projeto) {
		Calendar dataCorrente = Calendar.getInstance();
		Projeto prj = getProjeto(projeto);
		prj.setTitulo(projeto.getTitulo());
		prj.setDescricao(projeto.getDescricao());
		prj.setCreateDate(projeto.getCreateDate());
		prj.setDataInicio(projeto.getDataInicio());
		prj.setTarefas(projeto.getTarefas());
		prj.setUpdateDate(dataCorrente.getTime());
		projetoDao.update(prj);
	}
	
	public void excluirProjeto(Projeto projeto) {
		Projeto prj = getProjeto(projeto);
		projetoDao.delete(prj);
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
