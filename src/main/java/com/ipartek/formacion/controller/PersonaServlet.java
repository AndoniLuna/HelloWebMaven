package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.formacion.Constantes;
import com.ipartek.formacion.controller.listener.InitListener;
import com.ipartek.formacion.model.dao.PersistAble;
import com.ipartek.formacion.pojo.Persona;
import com.ipartek.formacion.service.ServicePersonaImplDB;

/**
 * Servlet implementation class PersonaServlet
 */
public class PersonaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(PersonaServlet.class);
	private RequestDispatcher dispatch;
	
	private PersistAble<Persona> servicePer = ServicePersonaImplDB.getInstance();

	/**
	 * Se ejecuta solo la primera vez que alguien llama al servlet
	 */
	public void init(ServletConfig config) throws ServletException {		
		super.init(config);	
	}

	/**
	 * Este metodo se ejecuta al Destruirse el servlet,
	 * por ejemplo cuando paramos el Servidor 
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int op = Integer.parseInt(request.getParameter("op"));
		
		switch (op) {
		case Constantes.OP_LIST:
			listar(request, response);
			break;
			
		case Constantes.OP_NEW:
			nuevo(request, response);
			break;
			
		case Constantes.OP_DETAIL:
			detalle(request, response);
			break;
			
		case Constantes.OP_DELETE:
			eliminar(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		dispatch.forward(request, response);

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("list", servicePer.getAll());
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PERSONA_LIST);
		
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) {		
		request.setAttribute("detail", new Persona() );
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PERSONA_DETAIL);
		
	}

	private void detalle(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("detail", servicePer.getById(id));
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PERSONA_DETAIL);		
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombrePersona = servicePer.getById(id).getNombre();
		String msg = "No se pudo eliminar a " + nombrePersona;
		
		if (servicePer.delete(id)){
			msg = "Persona &quot;" + nombrePersona + "&quot; eliminada correctamente";
		}
		
		request.setAttribute("msg",  msg);
		listar(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int op = Integer.parseInt(request.getParameter("op"));
		
		switch (op) {
		case Constantes.OP_SAVE:
			guardar(request, response);
			break;

		default:
			listar(request, response);
			break;
		}

		dispatch.forward(request, response);
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {
		
		//Recoger parametros formulario
		long id = Long.parseLong(request.getParameter("id"));		
		String nombre = request.getParameter("nombre");
		String email = request.getParameter("email");
		
		//Crear Persona		
		Persona p = new Persona();
		p.setId(id);
		p.setNombre(nombre);
		p.setEmail(email);	
		
		String msg = null;
		
		try {
//			servicePer.save(p);
		} catch (Exception e) {
			e.printStackTrace();
			msg="Error al guardar persona " + p.toString();
		}
		
		msg = "Los datos de "+nombre+" se han guardado correctamente.";
		request.setAttribute("msg", msg);
		request.setAttribute("detail", p);
		dispatch = request.getRequestDispatcher(Constantes.VIEW_PERSONA_DETAIL);
		
	}

}
