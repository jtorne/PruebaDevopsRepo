package es.rf.tienda.objetos.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.rf.tienda.dominio.Categoria;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfacesDAO;
import es.rf.tienda.util.HibernateUtil;
import es.rf.tienda.util.MontaSQL;
import es.rf.tienda.util.RFDataConnection;

public class CategoriaDAOH implements InterfacesDAO<Categoria> {

	private Session sesion;
	private Transaction tx;

	@Override
	public List<Categoria> leerTodos() throws DAOException {
		List<Categoria> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from Categoria").list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public List<Categoria> leerRegistros(Categoria clase) throws DAOException {
		String where = obtenWhere(clase);
		List<Categoria> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from Categoria " + where).list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public Categoria leerRegistro(Categoria clase) throws DAOException {
		List<Categoria> lista = leerRegistros(clase);
		if (lista.size() == 1) {
			return lista.get(0);
		} else
			throw new DAOException("Demasiados registros en Categorias");
	}

	@Override
	public int actualizarRegistro(Categoria clase) throws DAOException {
		try {
			iniciaOperacion();
			sesion.update(clase);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return 1;
	}

	@Override
	public int insertarRegistro(Categoria clase) throws DAOException, DomainException {
		try {
			iniciaOperacion();
			sesion.save(clase);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			if (sesion!=null)
				sesion.close();
		}
		return 1;
	}

	@Override
	public int borrarRegistro(Categoria clase) throws DAOException {
		try {
			iniciaOperacion();
			sesion.delete(clase);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			sesion.close();
		}
		return 1;
	}

	private String obtenWhere(Categoria clase) {

		String salida = obtenLista(clase, "AND");
		if (salida.length() > 0)
			salida = "WHERE " + salida;
		return salida;
	}

	private String obtenLista(Categoria clase, String separador) {
		String salida = "";
		if (clase.getId_categoria() > 0) {
			salida = MontaSQL.addToSalida(salida, "id_categoria", clase.getId_categoria(), separador);
		}
		if (clase.getCat_nombre() != null && clase.getCat_nombre().compareTo("") != 0) {
			salida = MontaSQL.addToSalida(salida, "cat_nombre", clase.getCat_nombre(), separador);
		}
		if(clase.getCat_descripcion()!=null && clase.getCat_descripcion().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "cat_descripcion", clase.getCat_descripcion(), separador);
		}
		return salida;
	}

	private void iniciaOperacion() throws HibernateException {
		sesion = HibernateUtil.getSession();
		tx = sesion.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
	}
}
