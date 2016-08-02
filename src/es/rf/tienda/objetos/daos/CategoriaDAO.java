package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfacesDAO;
import es.rf.tienda.util.MontaSQL;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.SentenciasSQL;

public class CategoriaDAO implements InterfacesDAO<Categoria> {

	private static final String SELECT = "SELECT * FROM Categoria ";
	private static final String UPDATE = "UPDATE Categoria SET ";
	private static final String INSERT = "INSERT INTO  Categoria VALUES ";
	private static final String DELETE = "DELETE FROM Categoria ";

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerTodos *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite obtener todas las categor�as de la BD
		 * 
		 * @return Una lista con todas las categor�as de la BD
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Categoria> leerTodos() throws DAOException, SQLException, DomainException {
		String sql= SELECT;
		return montaLista(sql);
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerRegistros *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite obtener todas las categor�as de la BD que cumplen el WHERE del SELECT a partir de los
		 * atributos de la categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los atributos que colocar en el WHERE
		 * @return Una lista con todas las categor�as de la DB que cumplan las condiciones del WHERE
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Categoria> leerRegistros(Categoria clase) throws DAOException, SQLException, DomainException {
		String where=obtenWhere(clase);
		String sql=SELECT+where;
		return montaLista(sql);
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerRegistro *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite obtener una categor�a de la BD que cumpla el WHERE del SELECT a partir de los
		 * atributos de la categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los atributos que colocar en el WHERE
		 * @return Una categor�a que cumpla las condiciones del WHERE, en caso que haya mas de una
		 * 			categor�a que cumpla las condicines, se devuelve la primera.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public Categoria leerRegistro(Categoria clase) throws DAOException, SQLException, DomainException {
		String where= obtenWhere(clase);
		String sql= SELECT+where;
		List<Categoria> listResult= montaLista(sql);
		if(!listResult.isEmpty())
			return listResult.get(0);
		else
			return null;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: actualizarRegistro *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite actualizar la categor�a de la BD con los valores recibidos de la
		 * categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los valores que se han de modificar
		 * @return El n�mero de filas afectas por el UPDATE. (1 � 0)
		 * 			1 Si se ha modifico una fila.
		 * 			0 Si no hay ninguna categor�a con el identificador indicado.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int actualizarRegistro(Categoria clase) throws DAOException {
		String set= obtenLista(clase,",","UPDATE");
		RFDataConnection dc= new RFDataConnection();
		int res= dc.ejecutar(UPDATE+set+ " WHERE ID_CATEGORIA="+ clase.getId_categoria());
		return res;
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: insertarRegistro *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite insertar una nueva categor�a en la BD con los valores recibidos de la
		 * categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a con los valores con los que se insertara en la BD
		 * @return El n�mero de filas afectas por el INSERT. (1 � 0)
		 * 			1 Si se ha creado una fila correctamente.
		 * 			0 Si no se ha podido crear la fila. 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int insertarRegistro(Categoria clase) throws DAOException {
		String ins= obtenLista(clase,",","INSERT");
		RFDataConnection dc= new RFDataConnection();
		int res= dc.ejecutar(INSERT+"("+ins+")");
		return res;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: borrarRegistro *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite borrar las categor�as de la BD que cumplan el WHERE del DELETE a partir de los
		 * atributos de la categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los atributos que colocar en el WHERE
		 * @return El n�mero de filas afectadas por el DELETE.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int borrarRegistro(Categoria clase) throws DAOException {
		String where = obtenWhere(clase);
		RFDataConnection dc= new RFDataConnection();
		int res= dc.ejecutar(DELETE+where);
		return res;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: obtenWhere *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite obtener el WHERE a partir de los atributos de la categor�a recibida que no est�n vac�os.
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los atributos que colocar en el WHERE
		 * @return Una string con el WHERE con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	private String obtenWhere(Categoria clase) {
		String salida="WHERE ";
		return salida+obtenLista(clase,"AND","WHERE");
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: obtenLista *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite obtener un String concatenada con todos los atributos y valores que se quieren 
		 * a�adir en el WHERE, VALUES i SET. Siendo estos atributos los atributos de la categor�a recibida
		 * que est�n vac�os o en cero. 
		 * 
		 * @param clase
		 *            Categoria Una categor�a para obtener los atributos que colocar en el WHERE, VALUES o SET
		 * @param separador
		 *            String Indica que tipo de separador tiene la operaci�n SQL, siendo ',' para UPDATE y INSERT
		 *            y 'AND' en el WHERE de las operaciones SELECT y DELETE          
		 * @param operacion
		 * 			  String Indica que tipos de operacion SQL es, si es un INSERT, UPDATE o la condicion WHERE.                  
		 * @return Una string con el WHERE, VALUES y SET con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	private String obtenLista(Categoria clase, String separador, String operacion) {
		String salida="";
		if(operacion.equals("INSERT")){
			salida=MontaSQL.addToSalida(salida, null, clase.getId_categoria(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getCat_nombre(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getCat_descripcion(), separador);
		}
		else{
			if(!operacion.equals("UPDATE") && clase.getId_categoria()>0){
				salida=MontaSQL.addToSalida(salida, "ID_CATEGORIA", clase.getId_categoria(), separador);
			}
			if(clase.getCat_nombre()!=null){
				salida=MontaSQL.addToSalida(salida, "CAT_NOMBRE", clase.getCat_nombre(), separador);
			}
			if(clase.getCat_descripcion()!=null){
				salida=MontaSQL.addToSalida(salida, "CAT_DESCRIPCION", clase.getCat_descripcion(), separador);
			}
		}
		return salida;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: montaRegistro *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite convertir en categor�a la categor�a recibida como ResultSet. 
		 * 
		 * @param rs
		 *            ResultSet El ResultSet que se quiere convertir en Categoria
		 * @return Una categor�a con los atributos obtenidos del ResultSet 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	private Categoria montaRegistro(ResultSet rs) throws SQLException, DomainException {
		Categoria result= new Categoria();
		result.setId_categoria(rs.getInt("ID_CATEGORIA"));
		result.setCat_nombre(rs.getString("CAT_NOMBRE"));
		if(rs.getString("CAT_DESCRIPCION")!=null){
			result.setCat_descripcion(rs.getString("CAT_DESCRIPCION"));
		}
		return result;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: montaLista *
	 * 
	 * DESCRIPCIéN:
	 *//**
		 * Permite convertir en una lista de categor�as las categor�a recibida como ResultSet 
		 * al efectuar una Query en la BD. 
		 * 
		 * @param sql
		 *            String La Query que se debe realizar.
		 * @return Una lista de categor�as que cumplan las condiciones de la Query recibida.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torn� - Barcelona
		 * 
		 **************************************************************************************/
	private List<Categoria> montaLista(String sql) throws DAOException, SQLException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		ResultSet rSet= dc.ejecutarQuery(sql);
		List<Categoria> listResult=new ArrayList<Categoria>();
		while(rSet.next()){
			listResult.add(montaRegistro(rSet));
		}
		return listResult;
	}
}
