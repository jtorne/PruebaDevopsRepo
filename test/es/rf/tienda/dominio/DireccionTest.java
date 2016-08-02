package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DomainException;

public class DireccionTest {

	Direccion d;
	
	@Before 
	public void inicio(){
		d= new Direccion();
	}
	
	@Test
	public void testSetDir_nombre() throws DomainException {
		String dir_nombre="NombreDir";
		d.setDir_nombre(dir_nombre);
		assertTrue(d.getDir_nombre().equals(dir_nombre));
	}
	@Test(expected=DomainException.class)
	public void testSetDir_nombre2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_nombre="NombreDir&%";
		d.setDir_nombre(dir_nombre);
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_nombre3() throws DomainException {
		//NULL
		String dir_nombre=null;
		d.setDir_nombre(dir_nombre);
	}

	@Test
	public void testSetDir_direccion() throws DomainException {
		String dir_direccion="Calle Falsa 123";
		d.setDir_direccion(dir_direccion);
		assertTrue(d.getDir_direccion().equals(dir_direccion));
	}
	@Test(expected=DomainException.class)
	public void testSetDir_direccion2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_direccion="Calle Falsa 123&%";
		d.setDir_direccion(dir_direccion);
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_direccion3() throws DomainException {
		//NULL
		String dir_direccion=null;
		d.setDir_direccion(dir_direccion);
	}
	@Test
	public void testSetDir_poblacion() throws DomainException {
		String dir_poblacion="Faketown";
		d.setDir_poblacion(dir_poblacion);
		assertTrue(d.getDir_poblacion().equals(dir_poblacion));
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_poblacion2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_poblacion="Faketown&%";
		d.setDir_poblacion(dir_poblacion);
	}

	@Test(expected=DomainException.class)
	public void testSetDir_poblacion3() throws DomainException {
		//NULL
		String dir_poblacion=null;
		d.setDir_poblacion(dir_poblacion);
	}

	@Test
	public void testSetDir_cPostal() throws DomainException {
		String dir_cPostal="80123";
		d.setDir_cPostal(dir_cPostal);
		assertTrue(d.getDir_cPostal().equals(dir_cPostal));
		
	}
	@Test(expected=DomainException.class)
	public void testSetDir_cPostal2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_cPostal="80123%/";
		d.setDir_cPostal(dir_cPostal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_cPostal3() throws DomainException {
		//NULL
		String dir_cPostal=null;
		d.setDir_cPostal(dir_cPostal);
	}


	@Test
	public void testSetDir_provincia() throws DomainException {
		String dir_provincia="Fakeland";
		d.setDir_provincia(dir_provincia);
		assertTrue(d.getDir_provincia().equals(dir_provincia));
	}
	@Test(expected=DomainException.class)
	public void testSetDir_provincia2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_provincia="Fakeland%&";
		d.setDir_provincia(dir_provincia);
	}

	@Test(expected=DomainException.class)
	public void testSetDir_provincia3() throws DomainException {
		//NULL
		String dir_provincia=null;
		d.setDir_provincia(dir_provincia);
	}

	
	@Test
	public void testSetDir_pais() throws DomainException {
		String dir_pais="España";
		d.setDir_pais(dir_pais);
		assertTrue(d.getDir_pais().equals(dir_pais));
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_pais2() throws DomainException {
		//NO ALFANUMERICOS
		String dir_pais="España&%";
		d.setDir_pais(dir_pais);
	}

	@Test(expected=DomainException.class)
	public void testSetDir_pais3() throws DomainException {
		//NULL
		String dir_pais=null;
		d.setDir_pais(dir_pais);
	}
	
	@Test
	public void testSetDir_correoE() throws DomainException {
		String dir_correoE="j.torne@mail.com";
		d.setDir_correoE(dir_correoE);
		assertTrue(d.getDir_correoE().equals(dir_correoE));
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_correoE2() throws DomainException {
		//SIN ARROBA
		String dir_correoE="j.tornemail.com";
		d.setDir_correoE(dir_correoE);
	}
	@Test(expected=DomainException.class)
	public void testSetDir_correoE3() throws DomainException {
		//SIN PUNTO FINAL
		String dir_correoE="j.torne@mailcom";
		d.setDir_correoE(dir_correoE);
	}
	@Test(expected=DomainException.class)
	public void testSetDir_correoE4() throws DomainException {
		//SIN SUFICIENTE FINAL
		String dir_correoE="j.torne@mail.c";
		d.setDir_correoE(dir_correoE);
	}
	@Test(expected=DomainException.class)
	public void testSetDir_correoE5() throws DomainException {
		//SIN NADA DESPUES DE ARROBA
		String dir_correoE="j.torne@.com";
		d.setDir_correoE(dir_correoE);
	}
	@Test(expected=DomainException.class)
	public void testSetDir_correoE6() throws DomainException {
		//SIN NADA ANTES DE ARROBA
		String dir_correoE="@am.com";
		d.setDir_correoE(dir_correoE);
	}
	
	@Test(expected=DomainException.class)
	public void testSetDir_correoE7() throws DomainException {
		//NULL
		String dir_correoE=null;
		d.setDir_correoE(dir_correoE);
	}

}
