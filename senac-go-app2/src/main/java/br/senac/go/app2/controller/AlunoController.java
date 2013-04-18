package br.senac.go.app2.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.senac.go.app2.model.Aluno;
import br.senac.go.app2.repository.AlunoRepository;

@Controller
public class AlunoController {
	// Injected via constructor 
	private AlunoRepository alunoRepository;

	@Autowired
	public AlunoController(@Qualifier("Objectify") AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	// GET /list
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response) {
		List<Aluno> alunos = alunoRepository.getAll();	    
	    request.setAttribute("alunoList",  alunos);
	    
	    return "/list";
	}

	// POST /update
	@RequestMapping(value = { "/update" }, method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		Aluno aluno = alunoRepository.getByEmail(email);
		aluno.setName(name);
		alunoRepository.update(aluno);
		
		return "redirect:/list";
	}
	
	// POST /create
	@RequestMapping(value = { "/create" }, method = RequestMethod.POST)
	public String create(HttpServletRequest request, HttpServletResponse response) {
		Aluno aluno = createAlunoFromRequest(request);
		if (aluno != null) {
			alunoRepository.persist(aluno);
		}
		
		return "redirect:/list";
	}
	
	// POST /delete
	@RequestMapping(value = { "/delete" }, method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		// Otimize! TODO: alunoRepository.deleteByEmail(email);
		Aluno aluno = alunoRepository.getByEmail(email);
		alunoRepository.delete(aluno);
		
		return "redirect:/list";
	}
	
	// GET /create
	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public String createForm(HttpServletRequest request, HttpServletResponse response) {			
		return "/create";
	}
	
	// GET /update
	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public String updateForm(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");		
		Aluno aluno = alunoRepository.getByEmail(email);
		request.setAttribute("aluno",  aluno);
		
		return "/update";
	}
	
	private Aluno createAlunoFromRequest(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		if (name == null || email == null) {
			return null;
		}
		if (name.equals("") || email.equals("")) {
			return null;
		}
		
		Date createdAt = new Date();
		
		Aluno aluno = new Aluno();
		aluno.setName(name);
		aluno.setEmail(email);
		aluno.setCreatedAt(createdAt);

		return aluno;
	}
}
