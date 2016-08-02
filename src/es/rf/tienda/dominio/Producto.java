package es.rf.tienda.dominio;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.TraductorCalendar;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre Producto Descripcion Modelo para los productos
 * 
 * @author Jordi Torné
 * @version Abril 2016
 *
 */
@Entity
public class Producto implements Serializable {
	@Id
	private String id_producto; // CÃ³digo de producto
	private String pro_descripcion; // Descripcion corta
	private String pro_desLarga; // Explicacion
	private double pro_precio; //Precio
	private int pro_stock; //Stock
	
	@Column(nullable = true)
	private Calendar pro_fecRepos; //Fecha prevista reposicion
	@Column(nullable = true)
	private Calendar pro_fecActi; //Fecha activacion
	@Column(nullable = true)
	private Calendar pro_fecDesacti; //Fecha desactivacion
	private String pro_uniVenta; //Unidad de venta
	private double pro_cantXUniVenta; //Cantidad de unidades ultimas
	private String pro_uniUltNivel; //Unidad ultima
	private int id_pais; //Pais de origen
	private String pro_usoRecomendado; //Uso recomendado
	
	/*@ManyToOne(targetEntity=es.rf.tienda.dominio.Categoria.class, cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name= "id_categoria", nullable = false)*/
	private int id_categoria; //Categoria
	private int pro_stkReservado; //Stock reservado
	private int pro_nStkAlto; //Stock para nivel alto
	private int pro_nStkBajo; //Stock para nivel bajo
	private char pro_stat; //Estado
	
	
	
	public static final int ID_PRODUCTO_LONGITUD_MIN=5;
	public static final int ID_PRODUCTO_LONGITUD_MAX=5;
	
	public static final int PRO_DESCRIPTION_LONGITUD_MIN=5;
	public static final int PRO_DESCRIPTION_LONGITUD_MAX=100;

	public static final int PRO_DESCLARGA_LONGITUD_MIN=5;
	public static final int PRO_DESCLARGA_LONGITUD_MAX=2000;
	
	public static final int PRO_PRECIO_RANGO_MIN=1;
	public static final int PRO_PRECIO_RANGO_MAX=100;
	
	public static final int PRO_UNIVENTA_LONGITUD_MIN=1;
	public static final int PRO_UNIVENTA_LONGITUD_MAX=10;

	public static final int PRO_USORECOMENDADO_LONGITUD_MIN=0;
	public static final int PRO_USORECOMENDADO_LONGITUD_MAX=2000;
	
	public static final int PRO_NSTKBAJO_MIN=0;
	
	public static final int PRO_NSTKRESERVADO_MIN=0;
	
	public boolean esValido() throws DomainException{
		if(getPro_stat()=='\u0000'){
			setPro_stat('A');//Valor default
		}
		//Falta validar id_pais y categoria
		if(getId_producto()==null || getPro_descripcion()==null || Validator.cumpleRango((int)getPro_precio(),PRO_PRECIO_RANGO_MIN,PRO_PRECIO_RANGO_MAX) || 
				getPro_uniVenta()==null){ 
			return false;
		}
		else 
			return true;
	}
	public String getId_producto() {
		return id_producto;
	}
	public void setId_producto(String id_producto) throws DomainException {
		if(Validator.isAlfanumeric(id_producto) && Validator.cumpleLongitud(id_producto,ID_PRODUCTO_LONGITUD_MIN,ID_PRODUCTO_LONGITUD_MAX)){
			this.id_producto = id_producto;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_028);
		}
		
	}
	public String getPro_descripcion() {
		return pro_descripcion;
	}
	public void setPro_descripcion(String pro_descripcion) throws DomainException {
		if(Validator.isAlfanumeric(pro_descripcion) && Validator.cumpleLongitud(pro_descripcion,PRO_DESCRIPTION_LONGITUD_MIN,PRO_DESCRIPTION_LONGITUD_MAX)){
			this.pro_descripcion = pro_descripcion;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_029);
		}
	}
	public String getPro_desLarga() {
		return pro_desLarga;
	}
	public void setPro_desLarga(String pro_desLarga) throws DomainException {
		if(Validator.isAlfanumeric(pro_desLarga) && Validator.cumpleLongitud(pro_desLarga,PRO_DESCLARGA_LONGITUD_MIN,PRO_DESCLARGA_LONGITUD_MAX)){
			this.pro_desLarga = pro_desLarga;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_030);
		}
	}
	public double getPro_precio() {
		return pro_precio;
	}
	public void setPro_precio(double pro_precio) throws DomainException {
		if(Validator.cumpleRango((int)pro_precio,PRO_PRECIO_RANGO_MIN,PRO_PRECIO_RANGO_MAX)){
			pro_precio = Math.round(pro_precio * 100.0d) / 100.0d;
			this.pro_precio = pro_precio;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_031);
		}
	}
	public int getPro_stock() {
		return pro_stock;
	}
	public void setPro_stock(int pro_stock) throws DomainException {
		if(Validator.cumpleRangoMin(pro_stock, 0)){
			this.pro_stock = pro_stock;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_006);
		}
		
	}
	public Calendar getPro_fecRepos() {
		return pro_fecRepos;
	}
	public void setPro_fecRepos(Calendar pro_fecRepos) throws DomainException {
		if(Validator.esFechaValida(pro_fecRepos,Calendar.getInstance())){
			this.pro_fecRepos = pro_fecRepos;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_032);
		}
	}
	public void setPro_fecRepos(String pro_fecRepos) throws DomainException{
		if(pro_fecRepos!=null){
			if(Validator.esFechaValida(pro_fecRepos, Calendar.getInstance())){
				this.pro_fecRepos = TraductorCalendar.StringToCalendar(pro_fecRepos);
			}
			else{
				throw new DomainException(ErrorMessages.ERM_032);
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_032);
		}
	}
	public Calendar getPro_fecActi() {
		return pro_fecActi;
	}
	public void setPro_fecActi(Calendar pro_fecActi) throws DomainException {
		if(pro_fecActi!=null){
			pro_fecActi.setLenient(false);
			if(Validator.esFechaValida(pro_fecActi,Calendar.getInstance())){
				this.pro_fecActi = pro_fecActi;
			}
			else{
				throw new DomainException(ErrorMessages.ERM_033);
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_033);
		}
	}
	public void setPro_fecActi(String pro_fecActi) throws DomainException{
		if(pro_fecActi!=null){
				if(Validator.esFechaValida(pro_fecActi,Calendar.getInstance())){
					this.pro_fecActi = TraductorCalendar.StringToCalendar(pro_fecActi);
				}
				else{
					throw new DomainException(ErrorMessages.ERM_033);
				}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_033);
		}
	}
	public Calendar getPro_fecDesacti() {
		return pro_fecDesacti;
	}
	public void setPro_fecDesacti(Calendar pro_fecDesacti) throws DomainException {
		if(pro_fecDesacti!=null){
			pro_fecDesacti.setLenient(false);
			if(getPro_fecActi()==null){
				if(Validator.esFechaValida(pro_fecDesacti,Calendar.getInstance())){
					this.pro_fecDesacti = pro_fecDesacti;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_034);
				}
			}
			else{
				if(Validator.esFechaValida(pro_fecDesacti,Calendar.getInstance()) && Validator.esFechaValida(pro_fecDesacti,getPro_fecActi())){
					this.pro_fecDesacti = pro_fecDesacti;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_034);
				}
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_034);
		}
	}
	public void setPro_fecDesacti(String pro_fecDesacti) throws DomainException{
		if(pro_fecDesacti!=null){
			if(getPro_fecActi()==null){
				if(Validator.esFechaValida(pro_fecDesacti,Calendar.getInstance())){
					this.pro_fecDesacti = TraductorCalendar.StringToCalendar(pro_fecDesacti);;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_034);
				}
			}
			else{
				if(Validator.esFechaValida(pro_fecDesacti,Calendar.getInstance()) && Validator.esFechaValida(pro_fecDesacti,Calendar.getInstance())){
					this.pro_fecDesacti = TraductorCalendar.StringToCalendar(pro_fecDesacti);;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_034);
				}
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_034);
		}
	}
	public String getPro_uniVenta() {
		return pro_uniVenta;
	}
	public void setPro_uniVenta(String pro_uniVenta) throws DomainException {
		if(Validator.isAlfanumeric(pro_uniVenta) && Validator.cumpleLongitud(pro_uniVenta,PRO_UNIVENTA_LONGITUD_MIN,PRO_UNIVENTA_LONGITUD_MAX)){
			this.pro_uniVenta = pro_uniVenta;
		}
		else {
			throw new DomainException(ErrorMessages.ERM_035);
		}
		
	}
	public double getPro_cantXUniVenta() {
		return pro_cantXUniVenta;
	}
	public void setPro_cantXUniVenta(double pro_cantXUniVenta) throws DomainException {
		if(Validator.cumpleRangoMin((int)pro_cantXUniVenta, 0)){
			pro_cantXUniVenta = Math.round(pro_cantXUniVenta * 100.0d) / 100.0d;
			this.pro_cantXUniVenta = pro_cantXUniVenta;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_016);
		}
			
	}
	public String getPro_uniUltNivel() {
		return pro_uniUltNivel;
	}
	public void setPro_uniUltNivel(String pro_uniUltNivel) throws DomainException {
		if(Validator.isAlfanumeric(pro_uniUltNivel)){
			this.pro_uniUltNivel = pro_uniUltNivel;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_036);
		}
		
	}
	public int getId_pais() {
		return id_pais;
	}
	public void setId_pais(int id_pais) {
		this.id_pais = id_pais;
	}
	public String getPro_usoRecomendado() {
		return pro_usoRecomendado;
	}
	public void setPro_usoRecomendado(String pro_usoRecomendado) throws DomainException {
		if(Validator.isAlfanumeric(pro_usoRecomendado) && Validator.cumpleLongitud(pro_usoRecomendado,PRO_USORECOMENDADO_LONGITUD_MIN,PRO_USORECOMENDADO_LONGITUD_MAX)){
			this.pro_usoRecomendado = pro_usoRecomendado;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_037);
		}
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) throws DomainException {
		if(Validator.cumpleRangoMin(id_categoria, 0)){
			this.id_categoria = id_categoria;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_005);
		}
	}
	public int getPro_stkReservado() {
		return pro_stkReservado;
	}
	public void setPro_stkReservado(int pro_stkReservado) throws DomainException {
		if(Validator.cumpleRangoMin(pro_stkReservado, PRO_NSTKRESERVADO_MIN)){
			this.pro_stkReservado = pro_stkReservado;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_007);
		}
	}
	public int getPro_nStkAlto() {
		return pro_nStkAlto;
	}
	public void setPro_nStkAlto(int pro_nStkAlto) throws DomainException {
		if(Validator.cumpleRangoMin(pro_nStkAlto, getPro_nStkBajo())){
			this.pro_nStkAlto = pro_nStkAlto;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_051);
		}
	}
	public int getPro_nStkBajo() {
		return pro_nStkBajo;
	}
	public void setPro_nStkBajo(int pro_nStkBajo) throws DomainException {
		if(getPro_nStkAlto()==0){
			if(Validator.cumpleRangoMin(pro_nStkBajo, PRO_NSTKBAJO_MIN)){
				this.pro_nStkBajo = pro_nStkBajo;
			}
			else{
				throw new DomainException(ErrorMessages.ERM_052);
			}
		}
		else if(Validator.cumpleRango(pro_nStkBajo, PRO_NSTKBAJO_MIN, getPro_nStkAlto())){
			this.pro_nStkBajo = pro_nStkBajo;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_052);
		}
	}
	public char getPro_stat() {
		return pro_stat;
	}
	public void setPro_stat(char pro_stat) throws DomainException {
		String texto= ""+pro_stat;
		if(Validator.isAlfanumeric(texto) && Validator.esEstadoValido(pro_stat)){
			this.pro_stat = pro_stat;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_038);
		}
	}

}
