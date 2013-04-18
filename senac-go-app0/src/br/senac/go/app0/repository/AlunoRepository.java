package br.senac.go.app0.repository;

import java.util.List;

import br.senac.go.app0.model.Aluno;

public interface AlunoRepository {
	void persist(Aluno aluno);
	void update(Aluno aluno);
	void delete(Aluno aluno);
	List<Aluno> getAll();
	Aluno getByEmail(String email);
}
