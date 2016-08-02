package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.dominio.Usuario;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfacesDAO;
import es.rf.tienda.util.MontaSQL;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.TraductorCalendar;

public class UsuarioDAO implements InterfacesDAO<Usuario> {

	private static final String SELECT = "SELECT * FROM USUARIOS ";
	private static final String UPDATE = "UPDATE USUARIOS SET ";
	private static final String INSERT = "INSERT INTO  USUARIOS VALUES ";
	private static final String DELETE = "DELETE FROM USUARIOS ";

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerTodos *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todos los usuarios de la BD
		 * 
		 * @return Una lista con todos los usuarios de la BD
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Usuario> leerTodos() throws DAOException, SQLException, DomainException {
		String sql= SELECT;
		return montaLista(sql);
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: leerRegistros *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite obtener todas los usuarios de la BD que cumplen el WHERE del SELECT a partir de los
		 * atributos del usuario que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los atributos que colocar en el WHERE
		 * @return Una lista con todos los usuarios de la DB que cumplan las condiciones del WHERE
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public List<Usuario> leerRegistros(Usuario clase) throws DAOException, SQLException, DomainException {
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
		 * Permite obtener un usuario de la BD que cumpla el WHERE del SELECT a partir de los
		 * atributos del usuario recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los atributos que colocar en el WHERE
		 * @return Un usuario que cumpla las condiciones del WHERE, en caso que haya mas de una
		 * 			usuario que cumpla las condicines, se devuelve la primera.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public Usuario leerRegistro(Usuario clase) throws DAOException, SQLException, DomainException {
		String where= obtenWhere(clase);
		String sql= SELECT+where;
		List<Usuario> listResult= montaLista(sql);
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
		 * Permite actualizar el usuario de la BD con los valores recibidos del
		 * usuario recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los valores que se han de modificar
		 * @return El número de filas afectas por el UPDATE. (1 ó 0)
		 * 			1 Si se ha modifico una fila.
		 * 			0 Si no hay ningún usuario con el identificador indicado.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int actualizarRegistro(Usuario clase) throws DAOException {
		String set= obtenLista(clase,",","UPDATE");
		RFDataConnection dc= new RFDataConnection();
		int res= dc.ejecutar(UPDATE+set+ " WHERE ID="+ clase.getId_usuario());
		return res;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: insertarRegistro *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite insertar un nuevo Registro en la BD con los valores recibidos del
		 * usuario recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario con los valores con los que se insertara en la BD
		 * @return El número de filas afectas por el INSERT. (1 ó 0)
		 * 			1 Si se ha creado una fila correctamente.
		 * 			0 Si no se ha podido crear la fila. 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int insertarRegistro(Usuario clase) throws DAOException {
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
		 * Permite borrar los usuarios de la BD que cumplan el WHERE del DELETE a partir de los
		 * atributos del usuario recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los atributos que colocar en el WHERE
		 * @return El número de filas afectadas por el DELETE.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	@Override
	public int borrarRegistro(Usuario clase) throws DAOException {
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
		 * Permite obtener el WHERE a partir de los atributos del usuario recibido que no estén vacíos.
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los atributos que colocar en el WHERE
		 * @return Una string con el WHERE con los atributos indicados.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private String obtenWhere(Usuario clase) {
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
		 * añadir en el WHERE, VALUES i SET. Siendo estos atributos los atributos del usuario recibida
		 * que estén vacíos o en cero. 
		 * 
		 * @param clase
		 *            Usuario Un usuario para obtener los atributos que colocar en el WHERE, VALUES o SET
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
	private String obtenLista(Usuario clase, String separador, String operacion) {
		String salida="";
		if(operacion.equals("INSERT")){
			salida=MontaSQL.addToSalida(salida, null, clase.getId_usuario(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getUser_nombre(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getUser_email(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getUser_pass(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getUser_tipo(), separador);
			salida=MontaSQL.addToSalida(salida, null, clase.getUser_dni(), separador);
			salida=MontaSQL.addToSalida(salida, null, TraductorCalendar.CalendarToString(clase.getUser_fecAlta()), separador);
			salida=MontaSQL.addToSalida(salida, null, TraductorCalendar.CalendarToString(clase.getUser_fecAlta()), separador);
		}
		else{
			if(!operacion.equals("UPDATE") && clase.getId_usuario()>0){
				salida=MontaSQL.addToSalida(salida, "ID", clase.getId_usuario(), separador);
			}
			if(clase.getUser_nombre()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_NOMBRE", clase.getUser_nombre(), separador);
			}
			if(clase.getUser_email()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_EMAIL", clase.getUser_email(), separador);
			}
			if(clase.getUser_pass()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_PASS", clase.getUser_pass(), separador);
			}
			if(clase.getUser_tipo()>0){
				salida=MontaSQL.addToSalida(salida, "USER_TIPO", clase.getUser_tipo(), separador);
			}
			if(clase.getUser_dni()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_DNI", clase.getUser_dni(), separador);
			}
			if(clase.getUser_fecAlta()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_FECALTA", TraductorCalendar.CalendarToString(clase.getUser_fecAlta()), separador);
			}
			if(clase.getUser_fecConfirmacion()!=null){
				salida=MontaSQL.addToSalida(salida, "USER_FECCONFIRMACION", TraductorCalendar.CalendarToString(clase.getUser_fecConfirmacion()), separador);
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
		 * Permite convertir en usuario el usuario recibido como ResultSet. 
		 * 
		 * @param rs
		 *            ResultSet El ResultSet que se quiere convertir en Usuario
		 * @return Un usuario con los atributos obtenidos del ResultSet 
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private Usuario montaRegistro(ResultSet rs) throws SQLException, DomainException {
		Usuario result= new Usuario();
		result.setId_usuario(rs.getInt("ID"));
		result.setUser_nombre(rs.getString("USER_NOMBRE"));
		result.setUser_email(rs.getString("USER_EMAIL"));
		result.setUser_pass(rs.getString("USER_PASS"));
		result.setUser_tipo(rs.getInt("USER_TIPO"));
		if(rs.getString("USER_DNI")!=null)result.setUser_dni(rs.getString("USER_DNI"));
		if(rs.getDate("USER_FECALTA")!=null){
			Calendar c= Calendar.getInstance();
			c.setTime(rs.getDate("USER_FECALTA"));
			result.setUser_fecAlta(c);
		}
		if(rs.getDate("USER_FECCONFIRMACION")!=null){
			Calendar c= Calendar.getInstance();
			c.setTime(rs.getDate("USER_FECCONFIRMACION"));
			result.setUser_fecConfirmacion(c);
		}
		return result;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: montaLista *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite convertir en una lista de usuario los usuarios recibidos como ResultSet 
		 * al efectuar una Query en la BD. 
		 * 
		 * @param sql
		 *            String La Query que se debe realizar.
		 * @return Una lista de usuarios que cumplan las condiciones de la Query recibida.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	private List<Usuario> montaLista(String sql) throws DAOException, SQLException, DomainException {
		RFDataConnection dc= new RFDataConnection();
		ResultSet rSet= dc.ejecutarQuery(sql);
		List<Usuario> listResult=new ArrayList<Usuario>();
		while(rSet.next()){
			listResult.add(montaRegistro(rSet));
		}
		return listResult;
	}

}
