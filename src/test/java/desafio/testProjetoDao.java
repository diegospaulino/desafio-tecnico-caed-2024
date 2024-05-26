package desafio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import br.com.crud.desafio.dao.ProjetoDao;
import br.com.crud.desafio.entity.Projeto;

public class testProjetoDao {
	
	private ProjetoDao projetoDao;
	private Integer id = 1;
	
	public void setProjetoDao(ProjetoDao projetoDao) {
		this.projetoDao = projetoDao;
	}
	
	private Projeto getProjeto() {
		Projeto projeto = new Projeto();
		projeto.setTitulo("Teste");
		return projeto;
	}

	@Test
	public void testUpdate() {
		Projeto projeto = projetoDao.getById(id);
		projeto.setTitulo("Teste 2");
		projeto = projetoDao.update(projeto);
		assertNotNull(projeto);
		assertEquals("Teste 2", projeto.getTitulo());
	}

	@Test
	public void testDelete() {
		Projeto projeto = projetoDao.getById(id);
		projetoDao.delete(projeto);
		assertNull(projetoDao.getById(id));
	}

	@Test
	public void testGetById() {
		Projeto projeto = projetoDao.getById(id);
		assertNotNull(projeto);
		assertEquals(id, projeto.getId());
		assertEquals("Teste", projeto.getTitulo());
	}

	@Test
	public void testSave() {
		Projeto projeto = null;
		projeto = projetoDao.save(getProjeto());
		assertNotNull(projeto);
		assertEquals(id, projeto.getId());
		assertEquals("Teste", projeto.getTitulo());
	}

	@Test
	public void testGetAll() {
		List<Projeto> projetos = projetoDao.getAll();
		assertNotNull(projetos);
		assertEquals(1, projetos.size());
		assertEquals("Teste 2", projetos.get(0).getTitulo());
	}

}
