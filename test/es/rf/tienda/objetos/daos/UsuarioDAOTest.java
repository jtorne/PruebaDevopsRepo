package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.SentenciasSQL;

public class UsuarioDAOTest {
	@Before
	public void inicio() throws SQLException{
		RFDataConnection dc= new RFDataConnection();
		dc= new RFDataConnection();
		try{
			dc.ejecutar("DROP TABLE PRODUCTO CONSTRAINTS");
		} catch (DAOException e) {
		}
		finally{
			try{
				dc.ejecutar("DROP TABLE CATEGORIA");
			}
			catch (DAOException e) {
			}
			finally{
				try{
					dc.ejecutar("DROP TABLE USUARIOS");
				}
				catch (DAOException e) {
				}
			}
		}
	}
	@Test
	public void testLeerTodos() throws DomainException, DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(1);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		int ret= dao.insertarRegistro(u);
		Usuario u2 = new Usuario();
		u2.setId_usuario(2);
		u2.setUser_nombre("jordi2");
		u2.setUser_email("2jordi@mail.com");
		u2.setUser_pass("pepito9%67A2");
		u2.setUser_fecAlta(Calendar.getInstance());
		dao.insertarRegistro(u2);
		dc.commit();
		List<Usuario> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistros() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(1);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		int ret= dao.insertarRegistro(u);
		Usuario u2 = new Usuario();
		u2.setId_usuario(2);
		u2.setUser_nombre("jordi");
		u2.setUser_email("2jordi@mail.com");
		u2.setUser_pass("pepito9%67A2");
		u2.setUser_fecAlta(Calendar.getInstance());
		dao.insertarRegistro(u2);
		Usuario claseWhere= new Usuario();
		claseWhere.setUser_nombre("jordi");
		dc.commit();
		List<Usuario> listResult=dao.leerRegistros(claseWhere);
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistro() throws DomainException, DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(1);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		int ret= dao.insertarRegistro(u);
		Usuario u2 = new Usuario();
		u2.setId_usuario(2);
		u2.setUser_nombre("jordi2");
		u2.setUser_email("2jordi@mail.com");
		u2.setUser_pass("pepito9%67A2");
		u2.setUser_fecAlta(Calendar.getInstance());
		dao.insertarRegistro(u2);
		Usuario claseWhere= new Usuario();
		claseWhere.setId_usuario(2);
		dc.commit();
		Usuario UsRet=dao.leerRegistro(claseWhere);
		assertTrue(UsRet.getId_usuario()==2);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

	@Test
	public void testActualizarRegistro() throws DomainException, DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(4936874);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		dao.insertarRegistro(u);
		u.setUser_nombre("torne");
		dao.actualizarRegistro(u);
		dc.commit();
		Usuario UsRet=dao.leerRegistro(u);
		assertTrue(UsRet.getUser_nombre().equals("torne"));
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

	@Test
	public void testInsertarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(4936874);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		int ret= dao.insertarRegistro(u);	
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

	@Test
	public void testBorrarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		Usuario u = new Usuario();
		u.setId_usuario(4936874);
		u.setUser_nombre("jordi");
		u.setUser_email("jordi@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_fecAlta(Calendar.getInstance());
		UsuarioDAO dao= new UsuarioDAO();
		dao.insertarRegistro(u);	
		int ret= dao.borrarRegistro(u);
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.closeConnection();
	}

}
