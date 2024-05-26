package br.com.crud.desafio.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.crud.desafio.dao.GenericDao;
import br.com.crud.desafio.entity.Projeto;

@Controller("projetoController")
@Scope("session")
public class ProjetoController {
	
	private Projeto projeto;
	
	@Resource
	private GenericDao<Projeto, Integer> projetoDao;

	public ProjetoController(Projeto projeto, GenericDao<Projeto, Integer> projetoDao) {
		super();
		this.projeto = projeto;
		this.projetoDao = projetoDao;
	}
	
	public String formProjeto() {
		this.projeto = new Projeto();
		return "formProjeto";
	}
	
	public List<Projeto> listaTodosProjetos() {
		List<Projeto> projetos = new ArrayList<>();
		projetos = projetoDao.getAll();
		return projetos;
	}
	
	public Projeto getProjeto(Integer id) {
		Projeto prj = projetoDao.getById(id);
		return prj;
	}
	
	public String editProjeto(Integer id) {
		projeto = getProjeto(id);
		return "formProjeto";
	}
	
	public String salvarProjeto() {
		Calendar dataCorrente = Calendar.getInstance();
		if(projeto.getId() == null) {
			projeto.setCreateDate(dataCorrente.getTime());
			projeto.setDataInicio(dataCorrente.getTime());
			projetoDao.save(projeto);
		}
		else {
			projeto.setUpdateDate(dataCorrente.getTime());
			projetoDao.update(projeto);
		}
		
		return "sucesso";
	}
	
	public String excluirProjeto(Integer id) {
		Projeto projeto = getProjeto(id);
		projetoDao.delete(projeto);
		
		return "listaProjetos";
	}
}
