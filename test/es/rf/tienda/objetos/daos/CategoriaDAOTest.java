package es.rf.tienda.objetos.daos;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.SentenciasSQL;

public class CategoriaDAOTest {
	@Before
	public void inicio() throws SQLException{
		RFDataConnection dc= new RFDataConnection();
		dc= new RFDataConnection();
		try{
			dc.ejecutar("DROP TABLE PRODUCTO CASCADE CONSTRAINTS");
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
	@Test
	public void testLeerTodos() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		Categoria clase2= new Categoria();
		clase2.setId_categoria(5657);
		clase2.setCat_nombre("NOMBREEEE22");
		clase2.setCat_descripcion("descripcion2");
		CategoriaDAO dao= new CategoriaDAO();
		dao.insertarRegistro(clase);	
		dao.insertarRegistro(clase2);
		dc.commit();
		List<Categoria> listResult=dao.leerTodos();
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistros() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		Categoria clase2= new Categoria();
		clase2.setId_categoria(5657);
		clase2.setCat_nombre("NOMBREEEE22");
		clase2.setCat_descripcion("descripcion");
		CategoriaDAO dao= new CategoriaDAO();
		dao.insertarRegistro(clase);	
		dao.insertarRegistro(clase2);
		Categoria claseWhere= new Categoria();
		claseWhere.setCat_descripcion("descripcion");
		dc.commit();
		List<Categoria> listResult=dao.leerRegistros(claseWhere);
		assertTrue(listResult.size()==2);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

	@Test
	public void testLeerRegistro() throws DAOException, DomainException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		Categoria clase2= new Categoria();
		clase2.setId_categoria(5657);
		clase2.setCat_nombre("NOMBREEEE22");
		clase2.setCat_descripcion("descripcion");
		CategoriaDAO dao= new CategoriaDAO();
		dao.insertarRegistro(clase);	
		dao.insertarRegistro(clase2);
		Categoria claseWhere= new Categoria();
		claseWhere.setCat_nombre("NOMBREEEE");
		dc.commit();
		Categoria CatRet=dao.leerRegistro(claseWhere);
		assertTrue(CatRet.getCat_nombre().equals("NOMBREEEE"));
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

	@Test
	public void testActualizarRegistro() throws DomainException, DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		CategoriaDAO dao= new CategoriaDAO();
		dao.insertarRegistro(clase);	
		clase.setCat_nombre("NOMBREEEE UPDATE");
		dao.actualizarRegistro(clase);
		Categoria CatRet=dao.leerRegistro(clase);
		assertTrue(CatRet.getCat_nombre().equals("NOMBREEEE UPDATE"));
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

	@Test
	public void testInsertarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		CategoriaDAO dao= new CategoriaDAO();
		int ret= dao.insertarRegistro(clase);	
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

	@Test
	public void testBorrarRegistro() throws DAOException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		Categoria clase= new Categoria();
		clase.setId_categoria(5656);
		clase.setCat_nombre("NOMBREEEE");
		clase.setCat_descripcion("descripcion");
		CategoriaDAO dao= new CategoriaDAO();
		dao.insertarRegistro(clase);	
		int ret= dao.borrarRegistro(clase);
		assertTrue(ret==1);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}

}
