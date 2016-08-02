package es.rf.tienda.interfaces.daos;

import java.sql.SQLException;
import java.util.List;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;

public interface InterfacesDAO<T> {

	/**
	 * Muestra todas las categorias
	 * 
	 * @return
	 * @throws DAOException
	 * @throws DomainException 
	 * @throws SQLException 
	 */
	public List<T> leerTodos() throws DAOException, SQLException, DomainException;

	public List<T> leerRegistros(T clase) throws DAOException, SQLException, DomainException;

	/**
	 * Devuelve una Categoria con el id que se le indica; si no existe, devuelve
	 * null
	 * 
	 * @param idCategoria
	 * @return
	 * @throws DAOException
	 * @throws DomainException 
	 * @throws SQLException 
	 */
	public T leerRegistro(T clase) throws DAOException, SQLException, DomainException;

	/**
	 * Actualiza la base de datos con el registro que se le pasa
	 * 
	 * @param categoria
	 * @return
	 * @throws DAOException
	 */
	public int actualizarRegistro(T clase) throws DAOException;

	/**
	 * Inserta en la base de datos el registro que se le pasa; devuelve el
	 * objeto con el id que le ha correspondido
	 * 
	 * @param categoria
	 * @return
	 * @throws DAOException
	 * @throws DomainException 
	 */
	public int insertarRegistro(T clase) throws DAOException, DomainException;

	/**
	 * Borra un registro de categoria que recibe como id
	 * 
	 * @param idCategoria
	 * @return
	 * @throws DAOException
	 */
	public int borrarRegistro(T clase) throws DAOException;

	/**
	 * Borra de la tabla de categoria el registro correspondiente al objeto que
	 * le pasan
	 * 
	 * @param categoria
	 * @return
	 * @throws DAOException
	 */

}
