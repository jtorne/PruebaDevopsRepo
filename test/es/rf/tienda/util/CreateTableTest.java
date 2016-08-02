package es.rf.tienda.util;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DAOException;

public class CreateTableTest {
	RFDataConnection dc;
	
	@Before
	public void inicio() throws SQLException{
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
					dc.ejecutar("DROP TABLE USUARIOS");
				}
				catch (DAOException e) {
				}
			}
		}
	}
	@Test
	public void testCreateTableCategoria() throws DAOException {
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.commit();
		dc.closeConnection();
	}
	@Test
	public void testInsertarTableCategoria() throws DAOException, SQLException {
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		dc.ejecutar("INSERT INTO categoria VALUES (50, 'NOMBRE','DESCRIPTION')");
		ResultSet res= dc.ejecutarQuery("SELECT * FROM categoria WHERE ID_CATEGORIA=50");
		res.next();
		assertTrue(res.getInt("ID_CATEGORIA")==50);
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.commit();
		dc.closeConnection();
	}
	
	@Test
	public void testInsertarTableCategoriaRollback() throws DAOException, SQLException {
		dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		dc.ejecutar("INSERT INTO categoria VALUES (50, 'NOMBRE','DESCRIPTION')");
		dc.rollback();
		ResultSet res= dc.ejecutarQuery("SELECT * FROM categoria WHERE ID_CATEGORIA=50");
		assertFalse(res.next());
		dc.ejecutar("DROP TABLE CATEGORIA");
		dc.closeConnection();
	}
	
	@Test
	public void testCreateTableProducto() throws DAOException {
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.commit();
		dc.closeConnection();
	}
	
	@Test
	public void testInsertTableProducto() throws DAOException, SQLException {
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		dc.ejecutar("INSERT INTO PRODUCTO (ID_PRODUCTO,PRO_DESCRIPCION,PRO_DESLARGA,PRO_PRECIO,PRO_UNIVENTA,ID_PAIS,ID_CATEGORIA) VALUES ('55555', 'DESCRIP','DESTION LARGA',45.06,'EUROS',1,1)");
		ResultSet res= dc.ejecutarQuery("SELECT * FROM PRODUCTO WHERE ID_PRODUCTO='55555'");
		res.next();
		assertTrue(res.getString("ID_PRODUCTO").equals("55555"));
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.commit();
		dc.closeConnection();
	}
	
	@Test
	public void testInsertTableProductoRollback() throws DAOException, SQLException {
		dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
		dc.ejecutar("INSERT INTO PRODUCTO (ID_PRODUCTO,PRO_DESCRIPCION,PRO_DESLARGA,PRO_PRECIO,PRO_UNIVENTA,ID_PAIS,ID_CATEGORIA) VALUES ('55555', 'DESCRIP','DESTION LARGA',45.06,'EUROS',1,1)");
		dc.rollback();
		ResultSet res= dc.ejecutarQuery("SELECT * FROM PRODUCTO WHERE ID_PRODUCTO='55555'");
		res.next();
		assertFalse(res.next());
		dc.ejecutar("DROP TABLE PRODUCTO");
		dc.closeConnection();
	}

	@Test
	public void testCreateTableUsuarios() throws DAOException {
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.commit();
		dc.closeConnection();
	}
	
	@Test
	public void testInsertTableUsuarios() throws DAOException, SQLException {
		dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
		dc.ejecutar("INSERT INTO USUARIOS (ID, USER_NOMBRE,USER_EMAIL,USER_PASS) VALUES (50, 'NOMBRE','EMAIL@MAIL.COM','PASSword0*')");
		ResultSet res= dc.ejecutarQuery("SELECT * FROM USUARIOS WHERE ID=50");
		res.next();
		assertTrue(res.getInt("ID")==50);
		dc.ejecutar("DROP TABLE USUARIOS");
		dc.commit();
		dc.closeConnection();
	}

}
