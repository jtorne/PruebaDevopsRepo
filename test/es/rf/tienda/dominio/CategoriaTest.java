package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DomainException;

public class CategoriaTest {
	Categoria cat;
	@Before 
	
	public void inicio(){
		cat= new Categoria();
	}
	/*@Test
	public void testEsValido() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSetId_categoria() throws DomainException {
		int id_categoria=45;
		cat.setId_categoria(id_categoria);
		assertTrue(cat.getId_categoria()==id_categoria);
	}
	@Test(expected=DomainException.class)
	public void testSetId_categoria2() throws DomainException {
		//NEGATIVO
		int id_categoria=-1;
		cat.setId_categoria(id_categoria);
	}
	@Test
	public void testSetCat_nombre() throws DomainException {
		String cat_nombre="Nombre de la categoria";
		cat.setCat_nombre(cat_nombre);
		assertTrue(cat.getCat_nombre().equals(cat_nombre));
	}
	@Test(expected=DomainException.class)
	public void testSetCat_nombre2() throws DomainException {
		//LONGITUD MENOR
		String cat_nombre="Nomb";
		cat.setCat_nombre(cat_nombre);
	}
	@Test(expected=DomainException.class)
	public void testSetCat_nombre3() throws DomainException {
		//LONGITUD MAYOR
		String cat_nombre="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. ";
		cat.setCat_nombre(cat_nombre);
	}
	@Test(expected=DomainException.class)
	public void testSetCat_nombre4() throws DomainException {
		//NO ALFANUMERICO
		String cat_nombre="Nombre&&";
		cat.setCat_nombre(cat_nombre);
	}

	@Test(expected=DomainException.class)
	public void testSetCat_nombre5() throws DomainException {
		//NULL
		String cat_nombre=null;
		cat.setCat_nombre(cat_nombre);
	}
	@Test
	public void testSetCat_descripcion() throws DomainException {
		String cat_descripcion="descripcion de la categoria";
		cat.setCat_descripcion(cat_descripcion);
		assertTrue(cat.getCat_descripcion().equals(cat_descripcion));
	}
	@Test(expected=DomainException.class)
	public void testSetCat_descripcion2() throws DomainException {
		//LONGITUD MAYOR
		String cat_descripcion="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut"
				+ "skdjghkds rsusgors jos sl. ";
		cat.setCat_descripcion(cat_descripcion);
	}
	@Test(expected=DomainException.class)
	public void testSetCat_descripcion3() throws DomainException {
		//NULL
		String cat_descripcion=null;
		cat.setCat_descripcion(cat_descripcion);
	}

}
