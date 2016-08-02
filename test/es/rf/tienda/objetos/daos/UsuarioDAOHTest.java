package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.Direccion;
import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;

public class UsuarioDAOHTest {
	
	Usuario u1;
	Usuario u2;
	UsuarioDAOH dao;
	Direccion d;
	
	@Before 
	public void inicio() throws DomainException, DAOException, SQLException{
		dao  = new UsuarioDAOH();
		borrarTablas();
		u1= new Usuario();
		u1.setUser_nombre("Jordi");
		u1.setUser_email("jordi@mail.com");
		u1.setUser_pass("pepito9%67A");
		u2= new Usuario();
		u2.setUser_nombre("Pepito");
		u2.setUser_email("pepito@mail.com");
		u2.setUser_pass("pepito9%67A");
		d= new Direccion();
		d.setDir_pais("Spain");
		u1.setUser_envio(d);
		u1.setUser_pago(d);
		u2.setUser_envio(d);
		u2.setUser_pago(d);
	}
	
	public void borrarTablas() throws SQLException, DAOException{
		RFDataConnection dc= new RFDataConnection();
		dc= new RFDataConnection();
		try{
			dc.ejecutar("DROP TABLE CARRITO CONSTRAINTS");
		} catch (DAOException e) {
		}
		finally{
		try{
			dc.ejecutar("DROP TABLE PRODUCTO CONSTRAINTS");
		} catch (DAOException e) {
		}
		finally{
			try{
				borrarInserts();
				dc.ejecutar("DROP TABLE USUARIO CONSTRAINTS");
			}
			catch (DAOException e) {
			}
			finally{
				try{
					dc.ejecutar("DROP TABLE CATEGORIA CONSTRAINTS");
				}
				catch (DAOException e) {
				}
			}
		}
		}
	}
	public void borrarInserts() throws DAOException{
		List<Usuario> listTodos=dao.leerTodos();
		for(int i=0; i<listTodos.size();i++){
			dao.borrarRegistro(listTodos.get(i));
		}
	}

	@Test
	public void testLeerTodos() throws DAOException, DomainException {
		dao.insertarRegistro(u1);
		dao.insertarRegistro(u2);
		List<Usuario> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		
		borrarInserts();
	}

	@Test
	public void testLeerRegistros() throws DomainException, DAOException {
		dao.insertarRegistro(u1);
		dao.insertarRegistro(u2);
		Usuario claseWhere= new Usuario();
		claseWhere.setUser_nombre("Jordi");
		List<Usuario> listResult=dao.leerRegistros(claseWhere);
		assertTrue(listResult.size()==1);
		
		borrarInserts();
	}
	

	@Test
	public void testLeerRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(u1);
		dao.insertarRegistro(u2);
		Usuario UsRes=dao.leerRegistro(u1);
		assertTrue(UsRes.getUser_nombre().equals(u1.getUser_nombre()));
		
		borrarInserts();
	}

	@Test
	public void testActualizarRegistro() throws DomainException, DAOException {
		dao.insertarRegistro(u1);
		Usuario uUpdate =dao.leerRegistro(u1);
		uUpdate.setUser_nombre("Juanito");
		dao.actualizarRegistro(uUpdate);
		Usuario cRes=dao.leerRegistro(uUpdate);
		assertTrue(cRes.getUser_nombre().equals(uUpdate.getUser_nombre()));
		
		borrarInserts();
	}

	@Test
	public void testInsertarRegistro() throws DAOException, DomainException {
		int ret= dao.insertarRegistro(u1);
		assertTrue(ret==1);
		
		borrarInserts();
	}

	@Test
	public void testBorrarRegistro() throws DAOException, DomainException, SQLException {
		dao.insertarRegistro(u1);
		List<Usuario> listTodos=dao.leerTodos();
		int ret= dao.borrarRegistro(listTodos.get(0));
		assertTrue(ret==1);
		
		borrarInserts();
	}

}
