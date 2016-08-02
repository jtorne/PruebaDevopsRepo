package es.rf.tienda.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import es.rf.tienda.exception.DAOException;

public class DataConnectionTest {
	
	public static String SQL_PRUEBA="SELECT USERNAME FROM ALL_USERS ORDER BY USERNAME";
	public static String SQL_PRUEBA1="SELECT TABLESPACE_NAME FROM USER_TABLESPACE";
	
	@Test
	public void testGetConnection() throws DAOException {	
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SQL_PRUEBA);
		dc.closeConnection();
	}

	@Test
	public void testCloseConnection() throws DAOException {
		RFDataConnection dc= new RFDataConnection();
		dc.closeConnection();
	}

	@Test
	public void testCommit() throws DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutarQuery(SQL_PRUEBA);
		dc.commit();
		dc.closeConnection();
	}

	@Test
	public void testRollback() throws DAOException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutarQuery(SQL_PRUEBA);
		dc.rollback();
		dc.closeConnection();
	}

	@Test
	public void testCloseStatementStatement() throws DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutarQuery(SQL_PRUEBA);
		dc.closeStatement();
		assertTrue(dc.getStm().isClosed());
		dc.closeConnection();
	}

	@Test
	public void testCloseResultSet() throws DAOException, SQLException {
		RFDataConnection dc= new RFDataConnection();
		ResultSet rs =dc.ejecutarQuery(SQL_PRUEBA);
		dc.closeResultSet(rs);
		assertTrue(rs.isClosed());
		dc.closeConnection();
	}

	@Test
	public void testEjecutar() throws DAOException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutar(SQL_PRUEBA);
		dc.closeConnection();
	}

	@Test
	public void testEjecutarQuery() throws DAOException {
		RFDataConnection dc= new RFDataConnection();
		dc.ejecutarQuery(SQL_PRUEBA);
		dc.closeConnection();
	}

	

	
	

}
