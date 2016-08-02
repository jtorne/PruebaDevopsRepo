package es.rf.tienda.objetos.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import es.rf.tienda.dominio.Producto;
import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.interfaces.daos.InterfacesDAO;
import es.rf.tienda.util.HibernateUtil;
import es.rf.tienda.util.MontaSQL;
import es.rf.tienda.util.TraductorCalendar;

public class ProductoDAOH implements InterfacesDAO<Producto> {

	private Session sesion;
	private Transaction tx;

	@Override
	public List<Producto> leerTodos() throws DAOException {
		List<Producto> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from Producto").list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public List<Producto> leerRegistros(Producto clase) throws DAOException {
		String where = obtenWhere(clase);
		List<Producto> lista = null;
		try {
			iniciaOperacion();
			lista = sesion.createQuery("from Producto " + where).list();
		} finally {
			sesion.close();
		}
		return lista;
	}

	@Override
	public Producto leerRegistro(Producto clase) throws DAOException {
		List<Producto> lista = leerRegistros(clase);
		if (lista.size() == 1) {
			return lista.get(0);
		} 
		else if(lista.size()==0){
			return null;
		}
		else{
			throw new DAOException("Demasiados registros en Producto");
		}
	}

	@Override
	public int actualizarRegistro(Producto clase) throws DAOException {
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
	public int insertarRegistro(Producto clase) throws DAOException, DomainException {

		try {
			iniciaOperacion();
			sesion.save(clase);
			tx.commit();
		} catch (HibernateException he) {
			he.printStackTrace();
			manejaExcepcion(he);
			throw he;
		} finally {
			if (sesion!=null)
				sesion.close();
		}
		return 1;
	}

	@Override
	public int borrarRegistro(Producto clase) throws DAOException {
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

	private String obtenWhere(Producto clase) {

		String salida = obtenLista(clase, "AND");
		if (salida.length() > 0)
			salida = "WHERE " + salida;
		return salida;
	}

	private String obtenLista(Producto clase, String separador) {
		String salida = "";
		if (clase.getId_producto()!=null && clase.getId_producto().compareTo("") != 0) {
			salida = MontaSQL.addToSalida(salida, "id_producto", clase.getId_producto(), separador);
		}
		if(clase.getPro_descripcion()!=null && clase.getPro_descripcion().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "pro_descripcion", clase.getPro_descripcion(), separador);
		}
		if(clase.getPro_desLarga()!=null && clase.getPro_desLarga().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "pro_desLarga", clase.getPro_desLarga(), separador);
		}
		if(clase.getPro_precio()>0){
			salida=MontaSQL.addToSalida(salida, "pro_precio", clase.getPro_precio(), separador);
		}
		if(clase.getPro_stock()>0){
			salida=MontaSQL.addToSalida(salida, "pro_stock", clase.getPro_stock(), separador);
		}
		if(clase.getPro_fecRepos()!=null){
			salida=MontaSQL.addToSalida(salida, "pro_fecRepos", TraductorCalendar.CalendarToString(clase.getPro_fecRepos()), separador);
		}
		if(clase.getPro_fecActi()!=null){
			salida=MontaSQL.addToSalida(salida, "pro_fecActi", TraductorCalendar.CalendarToString(clase.getPro_fecActi()), separador);
		}
		if(clase.getPro_fecDesacti()!=null){
			salida=MontaSQL.addToSalida(salida, "pro_fecDesacti", TraductorCalendar.CalendarToString(clase.getPro_fecDesacti()), separador);
		}
		if(clase.getPro_uniVenta()!=null && clase.getPro_uniVenta().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "pro_uniVenta", clase.getPro_uniVenta(), separador);
		}
		if(clase.getPro_cantXUniVenta()>0){
			salida=MontaSQL.addToSalida(salida, "pro_cantXUniVenta", clase.getPro_cantXUniVenta(), separador);
		}
		if(clase.getPro_uniUltNivel()!=null && clase.getPro_uniUltNivel().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "pro_uniUltNivel", clase.getPro_uniUltNivel(), separador);
		}
		if(clase.getId_pais()>0){
			salida=MontaSQL.addToSalida(salida, "id_pais", clase.getId_pais(), separador);
		}
		if(clase.getPro_usoRecomendado()!=null && clase.getPro_usoRecomendado().compareTo("") != 0){
			salida=MontaSQL.addToSalida(salida, "pro_usoRecomendado", clase.getPro_usoRecomendado(), separador);
		}
		if(clase.getId_categoria()>0){
			salida=MontaSQL.addToSalida(salida, "id_categoria", clase.getId_categoria(), separador);
		}
		if(clase.getPro_stkReservado()>0){
			salida=MontaSQL.addToSalida(salida, "pro_stkReservado", clase.getPro_stkReservado(), separador);
		}
		if(clase.getPro_nStkAlto()>0){
			salida=MontaSQL.addToSalida(salida, "pro_nStkAlto", clase.getPro_nStkAlto(), separador);
		}
		if(clase.getPro_nStkBajo()>0){
			salida=MontaSQL.addToSalida(salida, "pro_nStkBajo", clase.getPro_nStkBajo(), separador);
		}
		if(clase.getPro_stat()!='\0'){ 
			salida=MontaSQL.addToSalida(salida, "pro_stat", clase.getPro_stat(), separador);
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
