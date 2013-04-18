package br.senac.go.app0.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senac.go.app0.model.Aluno;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class AlunoRepositoryDatastoreImpl implements AlunoRepository {

	private DatastoreService datastoreService;

	public AlunoRepositoryDatastoreImpl(DatastoreService datastoreService) {
		this.datastoreService = datastoreService;
	}

	public void persist(Aluno aluno) {
		Entity entity = createEntityFromAluno(aluno);
		this.datastoreService.put(entity);
	}

	public void update(Aluno aluno) {
		Entity entity = getEntityByEmail(aluno.getEmail());
		entity.setPropertiesFrom(createEntityFromAluno(aluno));
		this.datastoreService.put(entity);
	}

	private Entity createEntityFromAluno(Aluno aluno) {
		Key key = KeyFactory.createKey("Aluno", aluno.getEmail());
		Entity entity = new Entity("Aluno", key);
		entity.setProperty("name", aluno.getName());
		entity.setProperty("email", aluno.getEmail());
		entity.setProperty("createdAt", aluno.getCreatedAt());
		return entity;
	}

	public void delete(Aluno aluno) {
		//Key key = KeyFactory.createKey("Aluno", aluno.getEmail());
		Entity entity = getEntityByEmail(aluno.getEmail());
		this.datastoreService.delete(entity.getKey());
	}

	public List<Aluno> getAll() {
		Query query = new Query("Aluno").addSort("createdAt",
				Query.SortDirection.DESCENDING);
		List<Entity> alunos = this.datastoreService.prepare(query).asList(
				FetchOptions.Builder.withLimit(10));

		return createAlunoFromEntity(alunos);
	}

	public Aluno getByEmail(String email) {
		Entity entity = getEntityByEmail(email);
		
		return createAlunoFromEntity(entity);
	}

	private Entity getEntityByEmail(String email) {
		Filter nameFilter = new FilterPredicate("email", FilterOperator.EQUAL,
				email);
		Query query = new Query("Aluno").setFilter(nameFilter);
		PreparedQuery preparedQuery = this.datastoreService.prepare(query);
		Entity entity = preparedQuery.asSingleEntity();
		
		return entity;
	}

	private Aluno createAlunoFromEntity(Entity entity) {
		if (entity == null) {
			return null;
		}
		
		Aluno aluno = new Aluno();
		aluno.setName((String) entity.getProperty("name"));
		aluno.setEmail((String) entity.getProperty("email"));
		aluno.setCreatedAt((Date) entity.getProperty("createdAt"));

		return aluno;
	}

	private List<Aluno> createAlunoFromEntity(List<Entity> entities) {
		List<Aluno> alunos = new ArrayList<Aluno>(entities.size());

		for (Entity entity : entities) {
			alunos.add(createAlunoFromEntity(entity));
		}

		return alunos;
	}

}
