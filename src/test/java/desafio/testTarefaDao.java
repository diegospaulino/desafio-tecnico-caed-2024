package desafio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import br.com.crud.desafio.dao.TarefaDao;
import br.com.crud.desafio.entity.Tarefa;

public class testTarefaDao {
	
	private TarefaDao tarefaDao;
	private Integer id = 1;
	
	public void setProjetoDao(TarefaDao tarefaDao) {
		this.tarefaDao = tarefaDao;
	}
	
	private Tarefa getTarefa() {
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao("Teste");
		return tarefa;
	}

	@Test
	public void testUpdate() {
		Tarefa tarefa = tarefaDao.getById(id);
		tarefa.setDescricao("Teste 2");
		tarefa = tarefaDao.update(tarefa);
		assertNotNull(tarefa);
		assertEquals("Teste 2", tarefa.getDescricao());
	}

	@Test
	public void testDelete() {
		Tarefa tarefa = tarefaDao.getById(id);
		tarefaDao.delete(tarefa);
		assertNull(tarefaDao.getById(id));
	}

	@Test
	public void testGetById() {
		Tarefa tarefa = tarefaDao.getById(id);
		assertNotNull(tarefa);
		assertEquals(id, tarefa.getId());
		assertEquals("Teste", tarefa.getDescricao());
	}

	@Test
	public void testSave() {
		Tarefa tarefa = null;
		tarefa = tarefaDao.save(getTarefa());
		assertNotNull(tarefa);
		assertEquals(id, tarefa.getId());
		assertEquals("Teste", tarefa.getDescricao());
	}

	@Test
	public void testGetAll() {
		List<Tarefa> tarefas = tarefaDao.getAll();
		assertNotNull(tarefas);
		assertEquals(1, tarefas.size());
		assertEquals("Teste 2", tarefas.get(0).getDescricao());
	}

}
