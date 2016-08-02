package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Producto;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.SentenciasSQL;

public class ProductoDAOTest {
	@Before
	public void inicio() throws SQLException{
		RFDataConnection dc= new RFDataConnection();
		dc= new RFDataConnection();
		try{
			dc.ejecutar("DROP TABLE CATEGORIA CONSTRAINTS");
		} catch (DAOException e) {
		}
		finally{
			try{
				dc.ejecutar("DROP TABLE PRODUCTO CASCADE CONSTRAINTS");
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
	@Test
	public void testLeerTodos() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12345");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		ProductoDAO dao= new ProductoDAO();
		dao.insertarRegistro(p);	
		
		Producto p2 = new Producto();
		p2.setId_producto("54321");
		p2.setPro_descripcion("descripcion");
		p2.setPro_precio(70.00d);
		p2.setPro_uniVenta("Dolares");
		p2.setId_pais(5);
		p2.setId_categoria(50);
		dao.insertarRegistro(p2);
		dc.commit();
		List<Producto> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistros() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12345");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		ProductoDAO dao= new ProductoDAO();
		dao.insertarRegistro(p);	
		
		Producto p2 = new Producto();
		p2.setId_producto("54321");
		p2.setPro_descripcion("descripcion");
		p2.setPro_precio(70.00d);
		p2.setPro_uniVenta("Dolares");
		p2.setId_pais(5);
		p2.setId_categoria(50);
		dao.insertarRegistro(p2);
		
		
		Producto claseWhere= new Producto();
		claseWhere.setPro_descripcion("descripcion");
		dc.commit();
		List<Producto> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistro() throws DomainException, DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12345");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		ProductoDAO dao= new ProductoDAO();
		dao.insertarRegistro(p);	
		
		Producto p2 = new Producto();
		p2.setId_producto("54321");
		p2.setPro_descripcion("descripcion");
		p2.setPro_precio(70.00d);
		p2.setPro_uniVenta("Dolares");
		p2.setId_pais(5);
		p2.setId_categoria(50);
		dao.insertarRegistro(p2);
		
		Producto claseWhere= new Producto();
		claseWhere.setId_producto("54321");
		dc.commit();
		Producto pResult= dao.leerRegistro(claseWhere);
		assertTrue(pResult.getId_producto().equals("54321"));
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
		
	}

	@Test
	public void testActualizarRegistro() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12345");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		ProductoDAO dao= new ProductoDAO();
		dao.insertarRegistro(p);
		p.setPro_uniVenta("Dolares");
		dao.actualizarRegistro(p);
		dc.commit();
		Producto pRes= dao.leerRegistro(p);
		assertTrue(pRes.getPro_uniVenta().equals("Dolares"));
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

	@Test
	public void testInsertarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12346");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		p.setPro_desLarga("ksjhf a hskhfaksha");
		p.setPro_stock(6);
		p.setPro_fecRepos(Calendar.getInstance());
		p.setPro_fecActi(Calendar.getInstance());
		p.setPro_fecRepos(Calendar.getInstance());
		p.setPro_cantXUniVenta(56.05d);
		p.setPro_uniUltNivel("UltNivel");
		p.setPro_usoRecomendado("uso recomendado");
		p.setPro_stkReservado(6);
		p.setPro_nStkAlto(20);
		p.setPro_nStkBajo(3);
		p.setPro_stat('A');
		ProductoDAO dao= new ProductoDAO();
		int ret= dao.insertarRegistro(p);	
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

	@Test
	public void testBorrarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		Producto p = new Producto();
		p.setId_producto("12345");
		p.setPro_descripcion("descripcion");
		p.setPro_precio(45.05d);
		p.setPro_uniVenta("Euros");
		p.setId_pais(5);
		p.setId_categoria(50);
		ProductoDAO dao= new ProductoDAO();
		dao.insertarRegistro(p);
		int ret= dao.borrarRegistro(p);
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

}
