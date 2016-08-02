package es.rf.tienda.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.ErrorMessages;
import es.rf.tienda.util.Validator;

/**
 * 
 * Nombre Categoria Descripcion Lista de categorÃ­as
 * 
 * @author Jordi Torné
 * @version Abril 2016
 *
 */
@Entity
public class Categoria implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id_categoria; // identificador categoria
	@Column(name="cat_nombre")
	private String cat_nombre; // nombre de la categoria
	@Column(name="cat_descripcion")
	private String cat_descripcion; // descripcion de la categoria

	public static final int CAT_NOMBRE_LONGITUD_MIN=5;
	public static final int CAT_NOMBRE_LONGITUD_MAX=50;
	
	public static final int CAT_DESCRIPCION_LONGITUD_MAX=200;
	
	public boolean esValido(){
		if(getCat_nombre()!=null) 
			return true;
		else 
			return false;
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

	public String getCat_nombre() {
		return cat_nombre;
	}

	public void setCat_nombre(String cat_nombre) throws DomainException {
		if(Validator.isAlfanumeric(cat_nombre) && Validator.cumpleLongitud(cat_nombre,CAT_NOMBRE_LONGITUD_MIN,CAT_NOMBRE_LONGITUD_MAX)){
			this.cat_nombre = cat_nombre;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_039);
		}
	}

	public String getCat_descripcion() {
		return cat_descripcion;
	}

	public void setCat_descripcion(String cat_descripcion) throws DomainException {
		if(Validator.isAlfanumeric(cat_descripcion) && Validator.cumpleLongitudMax(cat_descripcion,CAT_DESCRIPCION_LONGITUD_MAX)){
			this.cat_descripcion = cat_descripcion;
		}
		else{
			throw new DomainException(ErrorMessages.ERM_040);
		}
	}
}
