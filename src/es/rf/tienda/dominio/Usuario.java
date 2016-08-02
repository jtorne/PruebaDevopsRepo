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
import javax.persistence.Table;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.TraductorCalendar;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre Usuario Descripcion Datos de todo tipo de usuarios, clientes,....
 * 
 * @author Jordi Torné
 * @version Abril 2016
 *
 */
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id_usuario; // identificador de usuario
	private String user_nombre; // nombre de usuario
	 @Column(name = "user_email", unique=true)
	private String user_email; // Correo electronico del usuario
	private String user_pass; //Contraseña
	private int user_tipo; //Tipo de usuario
	private String user_dni; //Numero DNI
	private Calendar user_fecAlta; //FechaAlta
	private Calendar user_fecConfirmacion; //FechaConfirmacion
	
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
	private Direccion user_pago; // Datos direccion de pago
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
	private Direccion user_envio; // Datos direccion de envio
	
	public static final int USER_NOMBRE_LONGITUD_MIN=5;
	public static final int USER_NOMBRE_LONGITUD_MAX=100;
	
	public boolean esValido(){
		//idusuario
	 	if(getUser_nombre()==null || getUser_email()==null || getUser_pass()==null){
	 		return false;
	 	}
	 	else{
	 		return true;
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
			throw new DomainException(ErrorMessages.ERM_008);
		}
	}

	public String getUser_nombre() {
		return user_nombre;
	}

	public void setUser_nombre(String user_nombre) throws DomainException {
		if(Validator.isAlfanumeric(user_nombre) && Validator.cumpleLongitud(user_nombre,USER_NOMBRE_LONGITUD_MIN,USER_NOMBRE_LONGITUD_MAX)){
			this.user_nombre = user_nombre;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_041);
		}
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) throws DomainException {
		if(Validator.isEmailValido(user_email)){ 
			this.user_email = user_email;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_042);
		}
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) throws DomainException {
		if(Validator.esPasswordValida(user_pass)){
			this.user_pass = user_pass;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_043);
		}
	}

	public int getUser_tipo() {
		return user_tipo;
	}

	public void setUser_tipo(int user_tipo) throws DomainException {
		if(Validator.esTipoUsuarioValido(user_tipo)){
			this.user_tipo = user_tipo;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_044);
		}
	}

	public String getUser_dni() {
		return user_dni;
	}

	public void setUser_dni(String user_dni) throws DomainException {
		if(Validator.cumpleDNI(user_dni)){ 
		 this.user_dni = user_dni;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_045);
		}
	}

	public Calendar getUser_fecAlta() {
		return user_fecAlta;
	}

	public void setUser_fecAlta(Calendar user_fecAlta) throws DomainException {
		if(Validator.esFechaIgual(user_fecAlta,Calendar.getInstance())){
			this.user_fecAlta = user_fecAlta;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_046);
		}
	}
	public void setUser_fecAlta(String user_fecAlta) throws DomainException{
		if(user_fecAlta!=null){
				if(Validator.esFechaValida(user_fecAlta)){
					Calendar c= TraductorCalendar.StringToCalendar(user_fecAlta);
					if(Validator.esFechaIgual(c, Calendar.getInstance())){
						this.user_fecAlta = c;
					}
					else{
						throw new DomainException(ErrorMessages.ERM_046);
					}
				}
				else{
					throw new DomainException(ErrorMessages.ERM_046);
				}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_046);
		}
	}
	public Calendar getUser_fecConfirmacion() {
		return user_fecConfirmacion;
	}

	public void setUser_fecConfirmacion(Calendar user_fecConfirmacion) throws DomainException {
		if(Validator.esFechaIgual(user_fecConfirmacion,Calendar.getInstance())){
			this.user_fecConfirmacion = user_fecConfirmacion;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_047);
		}
	}
	public void setUser_fecConfirmacion(String user_fecConfirmacion) throws DomainException{
		if(user_fecConfirmacion!=null){
			if(Validator.esFechaValida(user_fecConfirmacion)){
				Calendar c= TraductorCalendar.StringToCalendar(user_fecConfirmacion);
				if(Validator.esFechaIgual(c, Calendar.getInstance())){
					this.user_fecConfirmacion = c;
				}
				else{
					throw new DomainException(ErrorMessages.ERM_047);
				}
			}
			else{
				throw new DomainException(ErrorMessages.ERM_047);
			}
		}
		else{
			throw new DomainException(ErrorMessages.ERM_047);
		}
	}
	public Direccion getUser_pago() {
		return user_pago;
	}

	public void setUser_pago(Direccion user_pago) {
		this.user_pago = user_pago;
	}

	public Direccion getUser_envio() {
		return user_envio;
	}

	public void setUser_envio(Direccion user_envio) {
		this.user_envio = user_envio;
	}

}