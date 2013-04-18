package br.senac.go.app2.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.senac.go.app2.model.Aluno;
import br.senac.go.app2.repository.AlunoRepository;
import br.senac.go.app2.repository.AlunoRepositoryDatastoreImpl;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TestAlunoRepository {
	
	private final LocalServiceTestHelper testHelper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	private AlunoRepository alunoRepository;
	
	@Before
	public void setUp() {
		testHelper.setUp();
		alunoRepository = new AlunoRepositoryDatastoreImpl(DatastoreServiceFactory.getDatastoreService());
	}
	
	@After
	public void tearDown() {
		testHelper.tearDown();
	}

	@Test
	public void testPersistAndGetByEmail() {		
		Aluno aluno = createAluno();
		alunoRepository.persist(aluno);
		
		Aluno alunoExpected = alunoRepository.getByEmail(aluno.getEmail());
		Assert.assertEquals(alunoExpected.getName(), aluno.getName());
		Assert.assertEquals(alunoExpected.getEmail(), aluno.getEmail());
		Assert.assertEquals(alunoExpected.getCreatedAt(), aluno.getCreatedAt());
	}
	
	@Test
	public void testBulkPersistAndGetByEmail() {		
		List<Aluno> alunos = createListAluno();
		for (Aluno aluno : alunos) {
			alunoRepository.persist(aluno);
		}
		
		for (Aluno aluno : alunos) {
			Aluno alunoExpected = alunoRepository.getByEmail(aluno.getEmail());
			Assert.assertEquals(alunoExpected.getName(), aluno.getName());
			Assert.assertEquals(alunoExpected.getEmail(), aluno.getEmail());
			Assert.assertEquals(alunoExpected.getCreatedAt(), aluno.getCreatedAt());
		}
	}
	
	@Test
	public void testPersistAndGetAll() {		
		Aluno aluno = createAluno();
		alunoRepository.persist(aluno);
		
		List<Aluno> alunosExpected = alunoRepository.getAll();
		for (Aluno alunoExpected : alunosExpected) {
			Assert.assertEquals(alunoExpected.getName(), aluno.getName());
			Assert.assertEquals(alunoExpected.getEmail(), aluno.getEmail());
			Assert.assertEquals(alunoExpected.getCreatedAt(), aluno.getCreatedAt());
		}
	}
	
	@Test
	public void testBulkPersistAndGetAll() {		
		List<Aluno> alunos = createListAluno();
		for (Aluno aluno : alunos) {
			alunoRepository.persist(aluno);
		}
		
		List<Aluno> alunosExpected = alunoRepository.getAll();
		Assert.assertTrue(alunos.containsAll(alunosExpected)); // Bad test =(
	}
	
	@Test
	public void testBulkPersistAndDelete() {		
		List<Aluno> alunos = createListAluno();
		for (Aluno aluno : alunos) {
			alunoRepository.persist(aluno);
		}
		
		for (Aluno aluno : alunos) {
			Aluno alunoExpected = alunoRepository.getByEmail(aluno.getEmail());
			Assert.assertEquals(alunoExpected.getName(), aluno.getName());
			Assert.assertEquals(alunoExpected.getEmail(), aluno.getEmail());
			Assert.assertEquals(alunoExpected.getCreatedAt(), aluno.getCreatedAt());
		}
		
		for (Aluno aluno : alunos) {
			alunoRepository.delete(aluno);
		}
		
		//Test 1
		Assert.assertEquals(0, alunoRepository.getAll().size());
		
		//Test 2
		for (Aluno aluno : alunos) {
			Aluno alunoExpected = alunoRepository.getByEmail(aluno.getEmail());
			Assert.assertNull(alunoExpected);
		}
	}
	
	@Test
	public void testBulkPersistAndUpdate() {		
		List<Aluno> alunos = createListAluno();
		for (Aluno aluno : alunos) {
			alunoRepository.persist(aluno);
		}
		
		for (Aluno aluno : alunos) {
			Aluno alunoExpected = alunoRepository.getByEmail(aluno.getEmail());
			Assert.assertEquals(alunoExpected.getName(), aluno.getName());
			Assert.assertEquals(alunoExpected.getEmail(), aluno.getEmail());
			Assert.assertEquals(alunoExpected.getCreatedAt(), aluno.getCreatedAt());
		}
		
		List<Aluno> updatedAlunos = updateListAluno(alunos);
		for (Aluno updatedAluno : updatedAlunos) {
			alunoRepository.update(updatedAluno);
		}
		
		for (Aluno updatedAluno : updatedAlunos) {
			Aluno alunoExpected = alunoRepository.getByEmail(updatedAluno.getEmail());
			Assert.assertEquals(alunoExpected.getName(), updatedAluno.getName());
			Assert.assertEquals(alunoExpected.getEmail(), updatedAluno.getEmail());
			Assert.assertEquals(alunoExpected.getCreatedAt(), updatedAluno.getCreatedAt());
		}
	}
	
	private Aluno createAluno() {
		Aluno aluno = new Aluno();
		aluno.setName("Aluno 0");
		aluno.setEmail("aluno0@google.com");
		aluno.setCreatedAt(new Date());
		
		return aluno;
	}
	
	private List<Aluno> createListAluno() {
		List<Aluno> alunos = new ArrayList<Aluno>(3);
		
		for (int i = 1; i <= 3; i++) {
			Aluno aluno = new Aluno();
			aluno.setName("Aluno " + i);
			aluno.setEmail("aluno" + i + "@google.com");
			aluno.setCreatedAt(new Date());
			
			alunos.add(aluno);
		}
		
		return alunos;
	}
	
	private List<Aluno> updateListAluno(List<Aluno> alunos) {
		List<Aluno> updatedAlunos = new ArrayList<Aluno>(3);
		for (int i = 4; i <= 6; i++) {
			Aluno aluno = new Aluno();
			aluno.setName("Aluno " + i);
			aluno.setEmail(alunos.get(i - 4).getEmail());
			aluno.setCreatedAt(alunos.get(i - 4).getCreatedAt());
			
			updatedAlunos.add(aluno);
		}
		
		return updatedAlunos;
	}

}
