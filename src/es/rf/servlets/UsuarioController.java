package es.rf.servlets;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.objetos.daos.UsuarioDAOH;
import es.rf.tienda.util.SentenciasSQL;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/Usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orden= request.getParameter("orden");
		HttpSession se= request.getSession();
		if(orden!=null && orden.equals("login")){	//SI LE HEMOS DADO A LOGIN
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			if(email!=null && pass!=null && !(email.equals("")) && !(pass.equals(""))){
				Usuario u = new Usuario();
				try {
					u.setUser_email(email);
					u.setUser_pass(pass);
					UsuarioDAOH dao= new UsuarioDAOH();
					Usuario uLogin=dao.leerRegistro(u);
					if(uLogin!=null){
						se.setAttribute("user", uLogin.getUser_nombre());
						se.setAttribute("idUser", uLogin.getId_usuario());
						se.setAttribute("email", uLogin.getUser_email());
						request.getRequestDispatcher("Usuario?orden=inicio").forward(request, response);
					}
					else{
						se.setAttribute("ErrorLogin", "No existe un usuario con estos parametros");
						request.getRequestDispatcher("/index.jsp").forward(request, response);
					}
				} catch (DomainException | DAOException e) {
					se.setAttribute("ErrorLogin", e.getMessage());
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
				
			}
			else{
				se.setAttribute("ErrorLogin", "Faltan campos obligatorios");
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		if(se.getAttribute("user")!=null){ //SI ESTA LOGEADO
			if(orden!=null && orden.equals("logout")){
				se.invalidate();
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
			else if(orden!=null && orden.equals("borrarse")){
				String email = se.getAttribute("email").toString();
				System.out.println("El mail es: "+ email);
				Usuario u= new Usuario();
				try {
					u.setUser_email(email);
					UsuarioDAOH dao= new UsuarioDAOH();
					Usuario uLogin=dao.leerRegistro(u);
					dao.borrarRegistro(uLogin);
				} catch (Exception e) {
					se.setAttribute("ErrorEliminar", e.getMessage());
				}
				finally{
					se.invalidate();
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}
			}
			else{ //Esta login y sin ninguna orden
				try{
					request.getRequestDispatcher("/inicio.jsp").forward(request, response);
				}
				catch (Exception e){
					
				}
			}
		}
		else{ //SI NO ESTA LOGEADO
			if(orden!=null && orden.equals("registrar")){ 
				String nombre= request.getParameter("nombre").toString();
				String email=request.getParameter("email").toString();
				String dni = request.getParameter("dni").toString();
				String password=request.getParameter("password").toString();
				String passwordConfirmation=request.getParameter("passwordConfirmation").toString();
				if(nombre!=null && email!=null && password!=null && passwordConfirmation!=null){
					request.setAttribute("nombre", nombre);
					request.setAttribute("email", email);
					request.setAttribute("dni", dni);
					if(!nombre.equals("") && !email.equals("") && !password.equals("") && !passwordConfirmation.equals("")){
						if(password.equals(passwordConfirmation)){
							Usuario u= new Usuario();
							try {
								u.setUser_nombre(nombre);
								u.setUser_email(email);
								if(dni!=null && dni!="") u.setUser_dni(dni);
								u.setUser_pass(password);
								u.setUser_fecAlta(Calendar.getInstance());
								UsuarioDAOH dao= new UsuarioDAOH();
								dao.insertarRegistro(u);
								se.setAttribute("user", nombre);
								se.setAttribute("email", email);
								request.getRequestDispatcher("/inicio.jsp").forward(request, response);
							} catch (DomainException | DAOException | HibernateException e) {
								se.setAttribute("ErrorRegistro", e.getMessage());
								request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
							}
						}
						else{
							se.setAttribute("ErrorRegistro", "Las contraseñas no son iguales");
							request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
						}
					}
					else{
						se.setAttribute("ErrorRegistro", "Faltan campos obligatorios");
						request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
					}
				}
				else{
					se.setAttribute("ErrorRegistro", "Faltan campos obligatorios");
					request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
				}
			}
			else {
				//request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
