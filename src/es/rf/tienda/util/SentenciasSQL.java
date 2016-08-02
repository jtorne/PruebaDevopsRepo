package es.rf.tienda.util;

import java.sql.SQLException;

import es.rf.tienda.exception.DAOException;

public class SentenciasSQL {
	
	public static final String CREA_PRODUCTO = "CREATE TABLE PRODUCTO ( " 
			+ " ID_PRODUCTO VARCHAR2(5) NOT NULL " 
			+ " , PRO_DESCRIPCION VARCHAR2(100) NOT NULL " 
			+ " , PRO_DESLARGA VARCHAR2(2000)  " 
			+ " , PRO_PRECIO NUMBER(5,2) NOT NULL" 
			+ " , PRO_STOCK NUMBER(5,2)" 
			+ " , PRO_FECREPOS DATE"  
			+ " , PRO_FECACTI DATE"  
			+ " , PRO_FECDESACTI DATE"  
			+ " , PRO_UNIVENTA VARCHAR2(2000) NOT NULL " 
			+ " , PRO_CANTXUNIVENTA NUMBER(5,2)" 
			+ " , PRO_CANTUNIULTNIVEL VARCHAR2(50)  " 
			+ " , ID_PAIS NUMBER NOT NULL" 
			+ " , PRO_USORECOMENDADO VARCHAR2(2000)  " 
			+ " , ID_CATEGORIA NUMBER NOT NULL" 
			+ " , PRO_STKRESERVADO NUMBER " 
			+ " , PRO_NSTKALTO NUMBER " 
			+ " , PRO_NSTKBAJO NUMBER " 
			+ " , PRO_STAT CHAR(1) "
			+ " , CONSTRAINT PRODUCTOS_PK PRIMARY KEY" 
			+ " ( ID_PRODUCTO ) ENABLE "  
			+ " )";
	
	public static final String CREA_CATEGORIA = "CREATE TABLE CATEGORIA ( " 
			+ " ID_CATEGORIA NUMBER NOT NULL " 
			+ " , CAT_NOMBRE VARCHAR2(100) NOT NULL " 
			+ " , CAT_DESCRIPCION VARCHAR2(100) NOT NULL " 
			+ " , CONSTRAINT CATEGORIA_PK PRIMARY KEY" 
			+ " ( ID_CATEGORIA ) ENABLE "  
			+ " )";
	
	public static final String CREA_USUARIOS = "CREATE TABLE USUARIOS ( " 
			+ " ID NUMBER NOT NULL " 
			+ " , USER_NOMBRE VARCHAR2(100) NOT NULL " 
			+ " , USER_EMAIL VARCHAR2(100) NOT NULL " 
			+ " , USER_PASS VARCHAR2(100) NOT NULL " 
			+ " , USER_TIPO NUMBER " 
			+ " , USER_DNI VARCHAR2(20) " 
			+ " , USER_FECALTA DATE"  
			+ " , USER_FECCONFIRMACION DATE" 
			+ " , CONSTRAINT USUARIOS_PK PRIMARY KEY" 
			+ " ( ID ) ENABLE "  
			+ " )";
	RFDataConnection dc;
	public void inicio() throws SQLException{
		dc= new RFDataConnection();
		try{
			dc.ejecutar(SentenciasSQL.CREA_CATEGORIA);
		} catch (DAOException e) {
		}
		finally{
			try{
				dc.ejecutar(SentenciasSQL.CREA_USUARIOS);
			}
			catch (DAOException e) {
			}
			finally{
				try{
					dc.ejecutar(SentenciasSQL.CREA_PRODUCTO);
				}
				catch (DAOException e) {
				}
			}
		}
		try {
			dc.commit();
			dc.closeConnection();
		}
		catch (Exception e){
			//Nothing
		}
	}
}
