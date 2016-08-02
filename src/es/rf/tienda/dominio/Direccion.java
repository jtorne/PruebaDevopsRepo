package es.rf.tienda.dominio;

import javax.persistence.Embeddable;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre Direccion Descripcion Modelo de datos para todas las direcciones
 * 
 * @author Jordi Torné
 * @version Abril 2016
 *
 */
@Embeddable 
public class Direccion {
	private String dir_nombre;  //Nombre
	private String dir_direccion;  //Direccion
	private String dir_poblacion;  //Poblacion
	private String dir_cPostal;  //Codigo postal
	private String dir_provincia; //Provincia
	private String dir_pais;  //Pais
	private String dir_correoE; //Correo electrónico

	public String getDir_nombre() {
		return dir_nombre;
	}
	public void setDir_nombre(String dir_nombre) throws DomainException {
		if(Validator.isAlfanumeric(dir_nombre)){
			this.dir_nombre = dir_nombre;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_009);
		}
	}
	public String getDir_direccion() {
		return dir_direccion;
	}
	public void setDir_direccion(String dir_direccion) throws DomainException {
		if(Validator.isAlfanumeric(dir_direccion)){
			this.dir_direccion = dir_direccion;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_010);
		}
	}
	public String getDir_poblacion() {
		return dir_poblacion;
	}
	public void setDir_poblacion(String dir_poblacion) throws DomainException {
		if(Validator.isAlfanumeric(dir_poblacion)){
			this.dir_poblacion = dir_poblacion;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_011);
		}
	}
	public String getDir_cPostal() {
		return dir_cPostal;
	}
	public void setDir_cPostal(String dir_cPostal) throws DomainException {
		if(Validator.isAlfanumeric(dir_cPostal)){
			this.dir_cPostal = dir_cPostal;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_012);
		}
	}
	public String getDir_provincia() {
		return dir_provincia;
	}
	public void setDir_provincia(String dir_provincia) throws DomainException {
		if(Validator.isAlfanumeric(dir_provincia)){
			this.dir_provincia = dir_provincia;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_013);
		}
	}
	public String getDir_pais() {
		return dir_pais;
	}
	public void setDir_pais(String dir_pais) throws DomainException {
		if(Validator.isAlfanumeric(dir_pais)){
			this.dir_pais = dir_pais;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_014);
		}
	}
	public String getDir_correoE() {
		return dir_correoE;
	}
	public void setDir_correoE(String dir_correoE) throws DomainException {
		if(Validator.isEmailValido(dir_correoE)){
			this.dir_correoE = dir_correoE;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_015);
		}
		
	}

}
