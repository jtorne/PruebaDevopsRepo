package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Producto;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;

public class ProductoDAOHTest {
	Producto p1;
	Producto p2;
	Producto p3;
	ProductoDAOH dao;

	@Before 
	public void inicio() throws DomainException, SQLException{
		borrarTablas();
		dao  = new ProductoDAOH();
		p1= new Producto();
		p1.setId_producto("12345");
		p1.setPro_descripcion("descripcion");
		p1.setPro_precio(45.05d);
		p1.setPro_uniVenta("Euros");
		p1.setId_pais(5);
		p1.setId_categoria(1);
		p2= new Producto();
		p2.setId_producto("12346");
		p2.setPro_descripcion("descripcion2");
		p2.setPro_precio(45.05d);
		p2.setPro_uniVenta("Euros");
		p2.setId_pais(8);
		p2.setId_categoria(1);
		p3= new Producto();
		p3.setId_producto("12347");
		p3.setPro_descripcion("descripcion3");
		p3.setPro_precio(45.05d);
		p3.setPro_uniVenta("Euros");
		p3.setId_pais(8);
		p3.setId_categoria(1);
		
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
				dc.ejecutar("DROP TABLE CATEGORIA");
			}
			catch (DAOException e) {
			}
			finally{
				try{
					dc.ejecutar("DROP TABLE USUARIO");
				}
				catch (DAOException e) {
				}
			}
		}
	}
	public void borrarInserts() throws DAOException{
		List<Producto> listTodos=dao.leerTodos();
		for(int i=0; i<listTodos.size();i++){
			dao.borrarRegistro(listTodos.get(i));
		}
	}

	@Test
	public void testLeerTodos() throws DAOException, DomainException {
		dao.insertarRegistro(p1);
		dao.insertarRegistro(p2);
		dao.insertarRegistro(p3);
		List<Producto> listResult=dao.leerTodos();
		assertTrue(listResult.size()==3);
		borrarInserts();
	}

	@Test
	public void testLeerRegistros() throws DAOException, DomainException, SQLException {
		dao.insertarRegistro(p1);
		dao.insertarRegistro(p2);
		Producto claseWhere= new Producto();
		claseWhere.setId_producto("12345");
		claseWhere.setPro_descripcion("descripcion");
		List<Producto> listResult=dao.leerRegistros(claseWhere);
		assertTrue(listResult.size()==1);
		
		borrarInserts();
		borrarTablas();
	}

	@Test
	public void testLeerRegistro() throws DAOException, DomainException, SQLException {
		dao.insertarRegistro(p1);
		dao.insertarRegistro(p2);
		Producto prodRes=dao.leerRegistro(p1);
		assertTrue(prodRes.getId_producto().equals(p1.getId_producto()));
		
		borrarInserts();
	}

	@Test
	public void testActualizarRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(p1);
		Producto pUpdate =dao.leerRegistro(p1);
		pUpdate.setPro_descripcion("New Description");
		dao.actualizarRegistro(pUpdate);
		Producto pRes=dao.leerRegistro(pUpdate);
		assertTrue(pRes.getPro_descripcion().equals(pUpdate.getPro_descripcion()));
		
		borrarInserts();
	}

	@Test
	public void testInsertarRegistro() throws DAOException, DomainException {
		int ret= dao.insertarRegistro(p1);
		assertTrue(ret==1);
		dao.borrarRegistro(p1);
		
	}

	@Test
	public void testBorrarRegistro() throws DAOException, DomainException {
		dao.insertarRegistro(p1);
		List<Producto> listTodos=dao.leerTodos();
		int ret= dao.borrarRegistro(listTodos.get(0));
		assertTrue(ret==1);
	}
	

}

