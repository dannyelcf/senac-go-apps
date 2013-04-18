package br.senac.go.app1.repository;

import java.util.List;

import br.senac.go.app1.model.Aluno;

public interface AlunoRepository {
	void persist(Aluno aluno);
	void update(Aluno aluno);
	void delete(Aluno aluno);
	List<Aluno> getAll();
	Aluno getByEmail(String email);
}
