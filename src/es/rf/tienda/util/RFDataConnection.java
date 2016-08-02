package es.rf.tienda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.rf.tienda.exception.DAOException;

public class RFDataConnection {
	/**
	 * Variable para obtener la conexion
	 */
	private static Connection conn;
	
	/**
	 * Statment para la conexion
	 */
	private Statement stm;
	/**
	 * Driver JDBC
	 */
	private final static String JDBC_DRIVER="oracle.jdbc.driver.OracleDriver";
	
	/**
	 * Direccion de la DB
	 */
	//private final static String URL ="jdbc:oracle:thin:@localhost:1521:xe";

	/**
	 * Nombre DB
	 */
	private final static String DATABASE= "";
	
	/**
	 * Usuario para la DB
	 */
	//private final static String USUARIO="alumno";
	//private final static String USUARIO=(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)?"alumno":"system";
	/**
	 * Password para la DB
	 */
	//private final static String PASSWORD="*******";
	private final static String SQL_USE= "USE";
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: getConnection *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener una conexion con la BD. Comprobando que la conexión no este
		 * abierta.
		 * 
		 * @return Devuelve un objeto Connection con la conexión de la BD.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private static Connection getConnection() throws DAOException{
		try{
			if(conn ==null || conn.isClosed()){
				try{
					Class.forName(JDBC_DRIVER); //Registrar el driver
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
				try{
					//conn= DriverManager.getConnection(URL,USUARIO,PASSWORD);
					conn= DriverManager.getConnection((System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)?"jdbc:oracle:thin:@localhost:1521:xe":"jdbc:oracle:thin:@192.***.***.***:1521:xe",(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)?"alumno":"system",(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)?"******":"***");
					conn.setAutoCommit(false);
				}catch(SQLException e){
					e.printStackTrace();
				}
				if(conn!=null){
					//System.out.println("Conexión lista");
				}else{
					System.out.println("Conexión erronea");
					throw new DAOException("No se ha podido conectar a la BD");
				}
			}
		}catch(SQLException e){
			System.out.println("Connexión erronea");
			throw new DAOException("No se ha podido conectar con la DB"); 
		}
		return conn;
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: closeConnection *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Cierra la conexión actual
		 *     
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static void closeConnection() throws DAOException{
		try{
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}catch(Exception e){
			System.out.println("No se ha podido cerrar");
			throw new DAOException("Error al cerrar la DB");
		}finally{
			conn=null;
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: commit *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Realiza un commit en la conexión actual
		 *     
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static void commit() throws DAOException{
		try{
			if(conn!=null){
				conn.commit();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error al realizar commit");
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: rollback *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Realiza un rollback en la conexión actual
		 *     
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static void rollback() throws DAOException{
		try{
			if(conn!=null){
				conn.rollback();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error al realizar rollback");
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: closeStatement *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Cierra el PreparadStatement que se pasa como argumento.
		 *     
		 * @param ps
		 * 			PreparedStatement PreparedStatement que se quiere cerrar    
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static void closeStatement(PreparedStatement ps) throws DAOException{
		try{
			if(ps!=null && !ps.isClosed()){
				ps.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error al realizar el cierre del PreparedStatement");
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: closeStatement *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Cierra el Statement de la conexión actual.
		 *     
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public void closeStatement() throws DAOException{
		try{
			if(stm!=null && !stm.isClosed()){
				stm.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error al realizar el cierre del Statement");
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: closeResultSet *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Cierra el ResultSet que se pasa como argumento.
		 *     
		 * @param r
		 * 			ResultSet ResultSet que se quiere cerrar    
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static void closeResultSet(ResultSet r) throws DAOException{
		try{
			if(r!=null && !r.isClosed()){
				r.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new DAOException("Error al realizar el cierre del ResultSet");
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: ejecutar *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite ejecutar una operación SQL sobre la DB.
		 * 
		 * @param sql
		 *            String La operación SQL que se quiere ejecutar
		 * @return Devuelve el número de filas afectas por la operación SQL.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public int ejecutar(String sql) throws DAOException{
		conn= getConnection();
		stm =null;
		int retorno;
		try{
			stm= conn.createStatement();
			retorno= stm.executeUpdate(sql);
		}catch(SQLException e){
			//e.printStackTrace();
			throw new DAOException("Error al realizar "+sql);
		}finally{
			closeStatement();
		}
		return retorno;
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: ejecutarQuery *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener el resultado de ejecutar una Query SQL sobre la DB.
		 * 
		 * @param sql
		 *            String La query SQL que se quiere ejecutar
		 * @return Devuelve un ResultSet con el resultado de ejecutar la query.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public ResultSet ejecutarQuery(String sql) throws DAOException{
		conn= getConnection();
		stm =null;
		ResultSet retorno;
		try{
			stm= conn.createStatement();
			retorno= stm.executeQuery(sql);
		}catch(SQLException e){
			//e.printStackTrace();
			throw new DAOException("Error al realizar "+sql);
		}finally{
			//closeStatement(stm); No se cierra porque tambien cierra el ResultSet
		}
		return retorno;
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: consigueClave *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener un clave única para un tabla en concreto.
		 * 
		 * @param tabla
		 *           String La tabla donde se quiere conseguir una clave única
		 * @param           
		 * 			 String El campo donde se quiere conseguir una clave única.
		 * @return Devuelve un ResultSet con el resultado de ejecutar la query.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public int consigueClave(String tabla, String campo) throws DAOException{
		String sql= "SELECT MAX ("+campo+") as num FROM "+tabla;
		int numero=0;
		ResultSet rs= ejecutarQuery(sql);
		try{
			if(rs!=null && !rs.isClosed() && rs.next())
				numero=rs.getInt("num")+1;
			else
				numero=1;
		}catch(SQLException e){
			e.printStackTrace();
			numero=1;
		}finally{
			RFDataConnection.commit();
			RFDataConnection.closeResultSet(rs);	
		}
		return numero;
	}

	public Statement getStm() {
		return stm;
	}
}
