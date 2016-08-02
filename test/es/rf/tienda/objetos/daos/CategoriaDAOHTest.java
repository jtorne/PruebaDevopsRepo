package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;

public class CategoriaDAOHTest {
	Categoria c1;
	Categoria c2;
	CategoriaDAOH dao;

	@Before 
	public void inicio() throws DomainException, SQLException{
		borrarTablas();
		dao  = new CategoriaDAOH();
		c1= new Categoria();
		c1.setCat_nombre("NombreCat1");
		c1.setCat_descripcion("descripcion");
		c2= new Categoria();
		c2.setCat_nombre("NombreCat2");
	}

	public void borrarTablas() throws SQLException{
		RFDataConnection dc= new RFDataConnection();
		dc= new RFDataConnection();
		try{
			dc.ejecutar("DROP TABLE PRODUCTO CONSTRAINTS");
		} catch (DAOException e) {
		}
		finally{
			try{
				dc.ejecutar("DROP TABLE CATEGORIA CONSTRAINTS");
			}
			catch (DAOException e) {
			}
			finally{
				try{
					dc.ejecutar("DROP TABLE USUARIO CONSTRAINTS");
				}
				catch (DAOException e) {
				}
			}
		}
	}
	public void borrarInserts() throws DAOException{

		List<Categoria> listTodos=dao.leerTodos();
		for(int i=0; i<listTodos.size();i++){
			dao.borrarRegistro(listTodos.get(i));
		}
	}
	@Test
	public void testTestLeerTodos() throws DAOException, DomainException {
		
		dao.insertarRegistro(c1);
		dao.insertarRegistro(c2);
		List<Categoria> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		
		borrarInserts();
	}

	@Test
	public void testTestLeerRegistros() throws DAOException, DomainException {
		dao.insertarRegistro(c1);
		dao.insertarRegistro(c2);
		Categoria claseWhere= new Categoria();
		claseWhere.setCat_descripcion("descripcion");
		List<Categoria> listResult=dao.leerRegistros(claseWhere);
		assertTrue(listResult.size()==1);
		
		borrarInserts();
	}

	
	@Test
	public void testTestLeerRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(c1);
		dao.insertarRegistro(c2);
		Categoria catRes=dao.leerRegistro(c1);
		assertTrue(catRes.getCat_nombre().equals(c1.getCat_nombre()));
		
		borrarInserts();

	}
	

	@Test
	public void testTestActualizarRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(c1);
		Categoria cUpdate =dao.leerRegistro(c1);
		cUpdate.setCat_descripcion("New Description");
		dao.actualizarRegistro(cUpdate);
		Categoria cRes=dao.leerRegistro(cUpdate);
		assertTrue(cRes.getCat_descripcion().equals(cUpdate.getCat_descripcion()));
		
		borrarInserts();
	}

	@Test
	public void testTestInsertarRegistro() throws DomainException, DAOException {
		int ret= dao.insertarRegistro(c1);	
		assertTrue(ret==1);
		
		borrarInserts();
	}

	@Test
	public void testTestBorrarRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(c1);
		
		List<Categoria> listTodos=dao.leerTodos();
		int ret= dao.borrarRegistro(listTodos.get(0));
		assertTrue(ret==1);
	}

}
