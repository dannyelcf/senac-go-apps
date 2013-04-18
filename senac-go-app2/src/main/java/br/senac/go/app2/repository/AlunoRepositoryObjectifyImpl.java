package br.senac.go.app2.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.senac.go.app2.model.Aluno;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Repository("Objectify")
public class AlunoRepositoryObjectifyImpl implements AlunoRepository {

	static {
        ObjectifyService.register(Aluno.class);
	}

	private Objectify objectify;

	@Autowired
	public AlunoRepositoryObjectifyImpl(Objectify objectify) {
		this.objectify = objectify;
	}

	public void persist(Aluno aluno) {
		objectify.save().entity(aluno);
	}

	public void update(Aluno aluno) {
		objectify.save().entity(aluno);
	}

	public void delete(Aluno aluno) {
		objectify.delete().entity(aluno);
	}

	public List<Aluno> getAll() {
		return objectify.load().type(Aluno.class).limit(10).list();
	}

	public Aluno getByEmail(String email) {
		return objectify.load().type(Aluno.class).filter("email", email).first().get();
	}
}
