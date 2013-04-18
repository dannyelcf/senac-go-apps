package br.senac.go.app1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.senac.go.app1.repository.AlunoRepository;
import br.senac.go.app1.repository.AlunoRepositoryDatastoreImpl;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

@SuppressWarnings("serial")
public class FrontControllerServlet extends HttpServlet {
	
	private static final String DEFAULT_VIEW = "/list";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String route = getRouteFromRequest(request);
		String view = DEFAULT_VIEW;
		
		// Inject Dependencies
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		AlunoRepository alunoRepository = new AlunoRepositoryDatastoreImpl(datastoreService);
		AlunoController alunoController = new AlunoController(alunoRepository);
		
		if (route.matches("^/list") || route.equals("/"))
			view = alunoController.list(request, response);
		else if (route.matches("^/update")) 
			view = alunoController.updateForm(request, response);
		else if (route.matches("^/create")) 
			view = alunoController.createForm(request, response);
		else if (route.matches("^/delete"))
			view = alunoController.delete(request, response);
		
		if (view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		} else {	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp" + view + ".jsp");
			dispatcher.forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String route = getRouteFromRequest(request);
		String view = DEFAULT_VIEW;
		
		// Inject Dependencies
		DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
		AlunoRepository alunoRepository = new AlunoRepositoryDatastoreImpl(datastoreService);
		AlunoController alunoController = new AlunoController(alunoRepository);
		
		if (route.matches("^/create"))
			view = alunoController.create(request, response);
		else if (route.matches("^/update")) 
			view = alunoController.update(request, response);
				
		if (view.startsWith("redirect:")) {
			response.sendRedirect(view.substring(9));
		} else {	
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp" + view + ".jsp");
			dispatcher.forward(request, response);
		}		
	}


	private String getRouteFromRequest(HttpServletRequest request) {
		String targetController = request.getRequestURI();
		return targetController != null ? targetController : "";
	}
}
