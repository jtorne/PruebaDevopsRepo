package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.rf.tienda.dominio.Producto;
import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfacesDAO;
import es.rf.tienda.util.MontaSQL;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.TraductorCalendar;

public class ProductoDAO implements InterfacesDAO<Producto>{

	private static final String SELECT = "SELECT * FROM PRODUCTO ";
	private static final String UPDATE = "UPDATE PRODUCTO SET ";
	private static final String INSERT = "INSERT INTO  PRODUCTO VALUES ";
	private static final String DELETE = "DELETE FROM PRODUCTO ";
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerTodos *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todas los productos de la BD
		 * 
		 * @return Una lista con todas los productos de la BD
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Producto> leerTodos() throws DAOException, SQLException, DomainException {
		String sql= SELECT;
		return montaLista(sql);
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerRegistros *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todas los productos de la BD que cumplen el WHERE del SELECT a partir de los
		 * atributos del producto que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los atributos que colocar en el WHERE
		 * @return Una lista con todos los productos de la DB que cumplan las condiciones del WHERE
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Producto> leerRegistros(Producto clase) throws DAOException, SQLException, DomainException {
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
		 * Permite obtener un producto de la BD que cumpla el WHERE del SELECT a partir de los
		 * atributos del producto recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los atributos que colocar en el WHERE
		 * @return Un producto que cumpla las condiciones del WHERE, en caso que haya mas de una
		 * 			producto que cumpla las condicines, se devuelve la primera.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public Producto leerRegistro(Producto clase) throws DAOException, SQLException, DomainException {
		String where= obtenWhere(clase);
		String sql= SELECT+where;
		List<Producto> listResult= montaLista(sql);
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
		 * Permite actualizar el producto de la BD con los valores recibidos del
		 * producto recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los valores que se han de modificar
		 * @return El número de filas afectas por el UPDATE. (1 ó 0)
		 * 			1 Si se ha modifico una fila.
		 * 			0 Si no hay ningún producto con el identificador indicado.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int actualizarRegistro(Producto clase) throws DAOException {
		String set= obtenLista(clase,",","UPDATE");
		RFDataConnection dc= new RFDataConnection();
		int res= dc.ejecutar(UPDATE+set+ " WHERE ID_PRODUCTO="+ clase.getId_producto());
		return res;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: insertarRegistro *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite insertar un nuevo Registro en la BD con los valores recibidos del
		 * producto recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto con los valores con los que se insertara en la BD
		 * @return El número de filas afectas por el INSERT. (1 ó 0)
		 * 			1 Si se ha creado una fila correctamente.
		 * 			0 Si no se ha podido crear la fila. 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int insertarRegistro(Producto clase) throws DAOException {
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
		 * Permite borrar los productos de la BD que cumplan el WHERE del DELETE a partir de los
		 * atributos del producto recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los atributos que colocar en el WHERE
		 * @return El número de filas afectadas por el DELETE.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int borrarRegistro(Producto clase) throws DAOException {
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
		 * Permite obtener el WHERE a partir de los atributos del producto recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los atributos que colocar en el WHERE
		 * @return Una string con el WHERE con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private String obtenWhere(Producto clase) {
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
		 * añadir en el WHERE, VALUES i SET. Siendo estos atributos los atributos del producto recibida
		 * que estén vacíos o en cero. 
		 * 
		 * @param clase
		 *            Producto Un producto para obtener los atributos que colocar en el WHERE, VALUES o SET
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
	private String obtenLista(Producto clase, String separador, String operacion) {
		String salida="";
		if(operacion.equals("INSERT")){
			salida=MontaSQL.addToSalida(salida, null, clase.getId_producto(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_descripcion(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_desLarga(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_precio(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_stock(), separador);
			salida=MontaSQL.addToSalida(salida, null, TraductorCalendar.CalendarToString(clase.getPro_fecRepos()), separador);
			salida=MontaSQL.addToSalida(salida, null, TraductorCalendar.CalendarToString(clase.getPro_fecActi()), separador);
			salida=MontaSQL.addToSalida(salida, null, TraductorCalendar.CalendarToString(clase.getPro_fecDesacti()), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_uniVenta(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_cantXUniVenta(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_uniUltNivel(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getId_pais(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_usoRecomendado(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getId_categoria(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_stkReservado(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_nStkAlto(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_nStkBajo(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getPro_stat(), separador);
		}
		else{
			if(!operacion.equals("UPDATE") && clase.getId_producto()!=null){
				salida=MontaSQL.addToSalida(salida, "ID_PRODUCTO", clase.getId_producto(), separador);
			}
			if(clase.getPro_descripcion()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_DESCRIPCION", clase.getPro_descripcion(), separador);
			}
			if(clase.getPro_desLarga()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_DESLARGA", clase.getPro_desLarga(), separador);
			}
			if(clase.getPro_precio()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_PRECIO", clase.getPro_precio(), separador);
			}
			if(clase.getPro_stock()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_STOCK", clase.getPro_stock(), separador);
			}
			if(clase.getPro_fecRepos()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_FECREPOS", TraductorCalendar.CalendarToString(clase.getPro_fecRepos()), separador);
			}
			if(clase.getPro_fecActi()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_FECACTI", TraductorCalendar.CalendarToString(clase.getPro_fecActi()), separador);
			}
			if(clase.getPro_fecDesacti()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_FECDESACTI", TraductorCalendar.CalendarToString(clase.getPro_fecDesacti()), separador);
			}
			if(clase.getPro_uniVenta()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_UNIVENTA", clase.getPro_uniVenta(), separador);
			}
			if(clase.getPro_cantXUniVenta()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_CANTXUNIVENTA", clase.getPro_cantXUniVenta(), separador);
			}
			if(clase.getPro_uniUltNivel()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_CANTUNIULTNIVEL", clase.getPro_uniUltNivel(), separador);
			}
			if(clase.getId_pais()>0){
				salida=MontaSQL.addToSalida(salida, "ID_PAIS", clase.getId_pais(), separador);
			}
			if(clase.getPro_usoRecomendado()!=null){
				salida=MontaSQL.addToSalida(salida, "PRO_USORECOMENDADO", clase.getPro_usoRecomendado(), separador);
			}
			if(clase.getId_categoria()>0){
				salida=MontaSQL.addToSalida(salida, "ID_CATEGORIA", clase.getId_categoria(), separador);
			}
			if(clase.getPro_stkReservado()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_STKRESERVADO", clase.getPro_stkReservado(), separador);
			}
			if(clase.getPro_nStkAlto()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_NSTKALTO", clase.getPro_nStkAlto(), separador);
			}
			if(clase.getPro_nStkBajo()>0){
				salida=MontaSQL.addToSalida(salida, "PRO_NSTKBAJO", clase.getPro_nStkBajo(), separador);
			}
			if(clase.getPro_stat()!='\0'){ 
				salida=MontaSQL.addToSalida(salida, "PRO_STAT", clase.getPro_stat(), separador);
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
		 * Permite convertir en producto el producto recibido como ResultSet. 
		 * 
		 * @param rs
		 *            ResultSet El ResultSet que se quiere convertir en Producto
		 * @return Un producto con los atributos obtenidos del ResultSet 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private Producto montaRegistro(ResultSet rs) throws SQLException, DomainException {
		Producto result= new Producto();
		result.setId_producto(rs.getString("ID_PRODUCTO"));
		result.setPro_descripcion(rs.getString("PRO_DESCRIPCION"));
		if(rs.getString("PRO_DESLARGA")!=null) result.setPro_desLarga(rs.getString("PRO_DESLARGA"));
		result.setPro_precio(rs.getDouble("PRO_PRECIO"));
		if(rs.getInt("PRO_STOCK")>0) result.setPro_stock(rs.getInt("PRO_STOCK"));
		if(rs.getDate("PRO_FECREPOS")!=null){
			Calendar c= Calendar.getInstance();
			c.setTime(rs.getDate("PRO_FECREPOS"));
			result.setPro_fecRepos(c);
		}
		if(rs.getDate("PRO_FECDESACTI")!=null){
			Calendar c= Calendar.getInstance();
			c.setTime(rs.getDate("PRO_FECDESACTI"));
			result.setPro_fecDesacti(c);
		}
		if(rs.getDate("PRO_FECDESACTI")!=null){
			Calendar c= Calendar.getInstance();
			c.setTime(rs.getDate("PRO_FECDESACTI"));
			result.setPro_fecDesacti(c);
		}
		result.setPro_uniVenta(rs.getString("PRO_UNIVENTA"));
		if(rs.getDouble("PRO_CANTXUNIVENTA")>0) result.setPro_cantXUniVenta(rs.getDouble("PRO_CANTXUNIVENTA"));
		if(rs.getString("PRO_CANTUNIULTNIVEL")!=null) result.setPro_uniUltNivel(rs.getString("PRO_CANTUNIULTNIVEL"));
		result.setId_pais(rs.getInt("ID_PAIS"));
		if(rs.getString("PRO_USORECOMENDADO")!=null) result.setPro_usoRecomendado(rs.getString("PRO_USORECOMENDADO"));
		result.setId_categoria(rs.getInt("ID_CATEGORIA"));
		if(rs.getInt("PRO_STKRESERVADO")>0) result.setPro_stkReservado(rs.getInt("PRO_STKRESERVADO"));
		if(rs.getInt("PRO_NSTKALTO")>0) result.setPro_nStkAlto(rs.getInt("PRO_NSTKALTO"));
		if(rs.getInt("PRO_NSTKBAJO")>0) result.setPro_nStkBajo(rs.getInt("PRO_NSTKBAJO"));
		if((rs.getString("PRO_STAT")).charAt(0)!='\0') result.setPro_nStkBajo((rs.getString("PRO_STAT")).charAt(0));
		return result;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: montaLista *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite convertir en una lista de producto los productos recibidos como ResultSet 
		 * al efectuar una Query en la BD. 
		 * 
		 * @param sql
		 *            String La Query que se debe realizar.
		 * @return Una lista de productos que cumplan las condiciones de la Query recibida.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private List<Producto> montaLista(String sql) throws DAOException, SQLException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		ResultSet rSet= dc.ejecutarQuery(sql);
		List<Producto> listResult=new ArrayList<Producto>();
		while(rSet.next()){
			listResult.add(montaRegistro(rSet));
		}
		return listResult;
	}

}
