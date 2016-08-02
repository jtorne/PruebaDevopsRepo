package es.rf.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.CategoriaDAOH;
import es.rf.tienda.util.SentenciasSQL;

/**
 * Servlet implementation class CategoriaController
 */
@WebServlet("/Categoria")
public class CategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orden= request.getParameter("orden");
		if(orden==null || orden.equals("listado")){
			HttpSession se= request.getSession();
			CategoriaDAOH dao = new CategoriaDAOH();
			List<Categoria> lista = null;
			try {
				lista=dao.leerTodos();
				if(lista!=null){
					se.setAttribute("listado", lista);
				}
			} catch (DAOException e) {
				e.printStackTrace();
			}
			response.sendRedirect("listaCategorias.jsp");
		}
		else if(orden!=null && orden.equals("crear")){
			CategoriaDAOH dao = new CategoriaDAOH();
			Categoria c= new Categoria();
			HttpSession se= request.getSession();
			String nombre= request.getParameter("nombreCat").toString();
			String descripcion= request.getParameter("descripcionCat").toString();
			try {
				c.setCat_nombre(nombre);
				if(descripcion!=null){
					c.setCat_descripcion(descripcion);
				}
				try {
					dao.insertarRegistro(c);
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
					
				} catch (DAOException | DomainException e) {
					request.getRequestDispatcher("nuevaCategoria.jsp").forward(request, response);
				}
			} catch (DomainException e) {
				se.setAttribute("ErrorCreateCat", e.getMessage());
				request.getRequestDispatcher("nuevaCategoria.jsp").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("leerCat")){
			if(request.getParameter("id_cat")!=null){
				int id_cat= Integer.parseInt(request.getParameter("id_cat"));
				CategoriaDAOH dao = new CategoriaDAOH();
				Categoria c= new Categoria();
				try {
					c.setId_categoria(id_cat);
					Categoria cLeida= dao.leerRegistro(c);
					HttpSession se= request.getSession();
					se.setAttribute("Categoria", cLeida);
					request.getRequestDispatcher("selectCat.jsp").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("update")){
			if(request.getParameter("id_cat")!=null){
				int id_cat= Integer.parseInt(request.getParameter("id_cat"));
				CategoriaDAOH dao = new CategoriaDAOH();
				Categoria c= new Categoria();
				try {
					c.setId_categoria(id_cat);
					String nombre= request.getParameter("nombreCat").toString();
					String descripcion= request.getParameter("descCat").toString();
					c.setCat_nombre(nombre);
					if(descripcion!=null){
						c.setCat_descripcion(descripcion);
					}
					dao.actualizarRegistro(c);
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
				} catch (DomainException | DAOException e) {
					HttpSession se= request.getSession();
					se.setAttribute("ErrorUpdateCat", e.getMessage());
					request.getRequestDispatcher("selectCat.jsp").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("leerCatElim")){
			if(request.getParameter("id_cat")!=null){
				int id_cat= Integer.parseInt(request.getParameter("id_cat"));
				CategoriaDAOH dao = new CategoriaDAOH();
				Categoria c= new Categoria();
				try {
					c.setId_categoria(id_cat);
					Categoria cLeida= dao.leerRegistro(c);
					HttpSession se= request.getSession();
					se.setAttribute("Categoria", cLeida);
					request.getRequestDispatcher("eliminarCategoria.jsp").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
			}
		}
		else if(orden!=null && orden.equals("eliminar")){
			if(request.getParameter("id_cat")!=null){
				int id_cat= Integer.parseInt(request.getParameter("id_cat"));
				CategoriaDAOH dao = new CategoriaDAOH();
				Categoria c= new Categoria();
				try {
					c.setId_categoria(id_cat);
					dao.borrarRegistro(c);
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
					
				} catch (DomainException | DAOException e) {
					request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
				}
			}
			else{
				request.getRequestDispatcher("Categoria?orden=listado").forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
