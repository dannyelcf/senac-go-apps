package br.senac.go.app2.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.senac.go.app2.model.Aluno;

public interface AlunoRepository {
	void persist(Aluno aluno);
	void update(Aluno aluno);
	void delete(Aluno aluno);
	List<Aluno> getAll();
	Aluno getByEmail(String email);
}
