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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todas las categorías de la BD
		 * 
		 * @return Una lista con todas las categorías de la BD
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todas las categorías de la BD que cumplen el WHERE del SELECT a partir de los
		 * atributos de la categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los atributos que colocar en el WHERE
		 * @return Una lista con todas las categorías de la DB que cumplan las condiciones del WHERE
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener una categoría de la BD que cumpla el WHERE del SELECT a partir de los
		 * atributos de la categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los atributos que colocar en el WHERE
		 * @return Una categoría que cumpla las condiciones del WHERE, en caso que haya mas de una
		 * 			categoría que cumpla las condicines, se devuelve la primera.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite actualizar la categoría de la BD con los valores recibidos de la
		 * categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los valores que se han de modificar
		 * @return El número de filas afectas por el UPDATE. (1 ó 0)
		 * 			1 Si se ha modifico una fila.
		 * 			0 Si no hay ninguna categoría con el identificador indicado.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite insertar una nueva categoría en la BD con los valores recibidos de la
		 * categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría con los valores con los que se insertara en la BD
		 * @return El número de filas afectas por el INSERT. (1 ó 0)
		 * 			1 Si se ha creado una fila correctamente.
		 * 			0 Si no se ha podido crear la fila. 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite borrar las categorías de la BD que cumplan el WHERE del DELETE a partir de los
		 * atributos de la categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los atributos que colocar en el WHERE
		 * @return El número de filas afectadas por el DELETE.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener el WHERE a partir de los atributos de la categoría recibida que no estén vacíos.
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los atributos que colocar en el WHERE
		 * @return Una string con el WHERE con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener un String concatenada con todos los atributos y valores que se quieren 
		 * añadir en el WHERE, VALUES i SET. Siendo estos atributos los atributos de la categoría recibida
		 * que estén vacíos o en cero. 
		 * 
		 * @param clase
		 *            Categoria Una categoría para obtener los atributos que colocar en el WHERE, VALUES o SET
		 * @param separador
		 *            String Indica que tipo de separador tiene la operación SQL, siendo ',' para UPDATE y INSERT
		 *            y 'AND' en el WHERE de las operaciones SELECT y DELETE          
		 * @param operacion
		 * 			  String Indica que tipos de operacion SQL es, si es un INSERT, UPDATE o la condicion WHERE.                  
		 * @return Una string con el WHERE, VALUES y SET con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite convertir en categoría la categoría recibida como ResultSet. 
		 * 
		 * @param rs
		 *            ResultSet El ResultSet que se quiere convertir en Categoria
		 * @return Una categoría con los atributos obtenidos del ResultSet 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite convertir en una lista de categorías las categoría recibida como ResultSet 
		 * al efectuar una Query en la BD. 
		 * 
		 * @param sql
		 *            String La Query que se debe realizar.
		 * @return Una lista de categorías que cumplan las condiciones de la Query recibida.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
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
