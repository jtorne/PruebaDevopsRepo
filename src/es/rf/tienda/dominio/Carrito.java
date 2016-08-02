package es.rf.tienda.dominio;

import java.util.Calendar;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.TraductorCalendar;
import es.rf.tienda.util.Validator;

@Entity
public class Carrito {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id_pedido; //identificador de pedido
	
	@OneToOne(targetEntity=Usuario.class)
	@JoinColumn(name= "id_usuario")
	private int id_usuario; //Identificador cliente
	
	@OneToOne(targetEntity=Producto.class)
	@JoinColumn(name= "id_producto")
	private String id_producto; //Codigo producto pedido
	private int car_cantidad; //Cantidad de productos que hay en una línea de producto
	private double car_precio; //Precio linea de  pedido
	
	@AttributeOverrides({
	    @AttributeOverride(name="dir_nombre",column=@Column(name="envio_nombre")),
	    @AttributeOverride(name="dir_direccion",column=@Column(name="envio_direccion")),
	    @AttributeOverride(name="dir_poblacion",column=@Column(name="envio_poblacion")),
	    @AttributeOverride(name="dir_cPostal",column=@Column(name="envio_cPostal")),
	    @AttributeOverride(name="dir_provincia",column=@Column(name="envio_provincia")),
	    @AttributeOverride(name="dir_pais",column=@Column(name="envio_pais")),
	    @AttributeOverride(name="dir_correoE",column=@Column(name="envio_correoE"))
	})
	@Embedded 
	private Direccion car_envio; //Datos de envio
	@AttributeOverrides({
	    @AttributeOverride(name="dir_nombre",column=@Column(name="pago_nombre")),
	    @AttributeOverride(name="dir_direccion",column=@Column(name="pago_direccion")),
	    @AttributeOverride(name="dir_poblacion",column=@Column(name="pago_poblacion")),
	    @AttributeOverride(name="dir_cPostal",column=@Column(name="pago_cPostal")),
	    @AttributeOverride(name="dir_provincia",column=@Column(name="pago_provincia")),
	    @AttributeOverride(name="dir_pais",column=@Column(name="pago_pais")),
	    @AttributeOverride(name="dir_correoE",column=@Column(name="pago_correoE"))
	})
	@Embedded 
	private Direccion car_pago; //Datos de pago
	private String car_tarjeta; //Numero de la tarjeta de crédito/débito
	private Calendar car_feCadud; //Fecha de caducidad de la tarjeta de crédito
	private int car_ccv;  //CCV de la tarjeta de crédito
	private String car_nombre; //Nombre del titular de la tarjeta
	private int car_stat; //Según tabla de situaciones de pedido
	private String[] car_FeCambio; //Un campo de fecha para cada situacion. Conserva la fecha del dia que entra
	
	public static final int CAR_TARJETA_LONGITUD=16;
	
	public static final int CAR_CCV_MIN=100;
	public static final int CAR_CCV_MAX=999;
	
	public static final int CAR_CANTIDAD_MIN=1;
	
	public static final int CAR_PRECIO_MIN=1;
	
	public  boolean esValido(){
		//id_usuario:"Existir en Usuarios, con rol cliente"
		//id_producto:"Existir en productos y con disponible > 0"
		//car_cantidad: >disponible
		if(getId_producto()==null || getCar_cantidad()<CAR_CANTIDAD_MIN){ 
			return false;
		}
		else{
			return true;
		}
	}
	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) throws DomainException {
		if(Validator.cumpleRangoMin(id_pedido, 0)){
			this.id_pedido = id_pedido;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_001);
		}
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) throws DomainException {
		if(Validator.cumpleRangoMin(id_usuario, 0)){
			this.id_usuario = id_usuario;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_002);
		}
	}

	public String getId_producto() {
		return id_producto;
	}

	public void setId_producto(String id_producto) throws DomainException {
		if(Validator.isAlfanumeric(id_producto)){
			this.id_producto = id_producto;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_028);
		}
	}

	public int getCar_cantidad() {
		return car_cantidad;
	}

	public void setCar_cantidad(int car_cantidad) throws DomainException {
		if(Validator.cumpleRangoMin(car_cantidad, CAR_CANTIDAD_MIN)){
			this.car_cantidad = car_cantidad;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_003);
		}
		
	}

	public double getCar_precio() {
		return car_precio;
	}

	public void setCar_precio(double car_precio) throws DomainException {
		if(Validator.cumpleRangoMin((int) car_precio, CAR_PRECIO_MIN)){
			this.car_precio = car_precio;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_004);
		}
	}

	public Direccion getCar_envio() {
		return car_envio;
	}

	public void setCar_envio(Direccion car_envio) {
		this.car_envio = car_envio;
	}

	public Direccion getCar_pago() {
		return car_pago;
	}

	public void setCar_pago(Direccion car_pago) {
		this.car_pago = car_pago;
	}

	public String getCar_tarjeta() {
		return car_tarjeta;
	}

	public void setCar_tarjeta(String car_tarjeta) throws DomainException {
		if(Validator.isAlfanumeric(car_tarjeta) && Validator.cumpleLongitud(car_tarjeta,CAR_TARJETA_LONGITUD,CAR_TARJETA_LONGITUD)){
			this.car_tarjeta = car_tarjeta;
		}
		else {
			throw new DomainException(ErrorMessages.ERM_048);
		}
	}

	public Calendar getCar_feCadud() {
		return car_feCadud;
	}

	public void setCar_feCadud(Calendar car_feCadud) throws DomainException {
		if(Validator.esFechaMayorQue(car_feCadud, Calendar.getInstance())){
			this.car_feCadud = car_feCadud;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_049);
		}
	}

	public void setCar_feCadud(String car_feCadud) throws DomainException{
		if(car_feCadud!=null){
			if(Validator.esFechaValida(car_feCadud,Calendar.getInstance())){
				Calendar c= TraductorCalendar.StringToCalendar(car_feCadud);
				if(Validator.esFechaMayorQue(c, Calendar.getInstance())){
					this.car_feCadud = c;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_049);
				}
			}
			else{
				throw new DomainException(ErrorMessages.ERM_049);
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_049);
		}
	}
	
	public int getCar_ccv() {
		return car_ccv;
	}

	public void setCar_ccv(int car_ccv) throws DomainException {
		if(Validator.cumpleRango(car_ccv, CAR_CCV_MIN, CAR_CCV_MAX)){
			this.car_ccv = car_ccv;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_050);
		}
	}

	public String getCar_nombre() {
		return car_nombre;
	}

	public void setCar_nombre(String car_nombre) throws DomainException {
		if(Validator.isAlfanumeric(car_nombre)){
			this.car_nombre = car_nombre;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_017); 
		}
	}

	public int getCar_stat() {
		return car_stat;
	}

	public void setCar_stat(int car_stat) throws DomainException {
		if(Validator.esEstadoPedidoValido(car_stat)){
			this.car_stat = car_stat;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_018);
		}
	}

	public String[] getCar_FeCambio() {
		return car_FeCambio;
	}

	public void setCar_FeCambio(String[] car_FeCambio) throws DomainException {
		if(car_FeCambio!=null){
			for(int i=0; i< car_FeCambio.length;i++){
				if(!Validator.esFechaValida(car_FeCambio[i])){
					throw new DomainException(ErrorMessages.ERM_019);
				}
			}
			this.car_FeCambio = car_FeCambio;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_019);
		}
	}
	

}
