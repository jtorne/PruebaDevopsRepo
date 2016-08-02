package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DAOException;
import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.RFDataConnection;
import es.rf.tienda.util.TraductorCalendar;

public class UsuarioTest {
	Usuario u;
	
	@Before 
	public void inicio(){
		u= new Usuario();
	}
	
	@Test
	public void testSetId_usuario() throws DomainException {
		int id_usuario=3;
		u.setId_usuario(id_usuario);
		assertTrue(u.getId_usuario()==id_usuario);
	}
	
	@Test(expected=DomainException.class)
	public void testSetId_usuario2() throws DomainException {
		//NEGATIVO
		int id_usuario=-3;
		u.setId_usuario(id_usuario);
	}

	@Test
	public void testSetUser_nombre() throws DomainException {
		String user_nombre="jordi";
		u.setUser_nombre(user_nombre);
		assertTrue(u.getUser_nombre().equals(user_nombre));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_nombre2() throws DomainException {
		//LONGITUD MENOR
		String user_nombre="alex";
		u.setUser_nombre(user_nombre);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_nombre3() throws DomainException {
		//LONGITUD MAYOR
		String user_nombre="nombremuylargoparaverquesaleexception dsgkhkgsdgdsg"
				+ "djghjsdgdsgsdgskkdhkdskghkgsdgdksgjkdsghkjdshgkdshgkdshgk";
		u.setUser_nombre(user_nombre);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_nombre4() throws DomainException {
		//NULL
		String user_nombre=null;
		u.setUser_nombre(user_nombre);
	}

	@Test
	public void testSetUser_email() throws DomainException {
		String user_email="j.torne@mail.com";
		u.setUser_email(user_email);
		assertTrue(u.getUser_email().equals(user_email));
	}
	@Test(expected=DomainException.class)
	public void testSetUser_email2() throws DomainException {
		//SIN ARROBA
		String user_email="j.tornemail.com";
		u.setUser_email(user_email);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_email3() throws DomainException {
		//SIN PUNTO FINAL
		String user_email="j.torne@mailcom";
		u.setUser_email(user_email);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_email4() throws DomainException {
		//SIN SUFICIENTE FINAL
		String user_email="j.torne@mail.c";
		u.setUser_email(user_email);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_email5() throws DomainException {
		//SIN NADA DESPUES DE ARROBA
		String user_email="j.torne@.com";
		u.setUser_email(user_email);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_email6() throws DomainException {
		//SIN NADA ANTES DE ARROBA
		String user_email="@am.com";
		u.setUser_email(user_email);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_email7() throws DomainException {
		//NULL
		String user_email=null;
		u.setUser_email(user_email);
	}
	@Test
	public void testSetUser_pass() throws DomainException {
		String user_pass="pepito9%67A";
		u.setUser_pass(user_pass);
		assertTrue(u.getUser_pass().equals(user_pass));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass2() throws DomainException {
		//SIN MAYUSCULAS
		String user_pass="pepito9%67";
		u.setUser_pass(user_pass);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass3() throws DomainException {
		//SIN ESPECIALES
		String user_pass="pepito67A";
		u.setUser_pass(user_pass);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass4() throws DomainException {
		//SIN NUMEROS
		String user_pass="pepitogdgdd%A";
		u.setUser_pass(user_pass);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass5() throws DomainException {
		//SIN MINUSCULAS
		String user_pass="PEPITO9%67A";
		u.setUser_pass(user_pass);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass6() throws DomainException {
		//LONGITUD MENOR
		String user_pass="pE%2a";
		u.setUser_pass(user_pass);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_pass7() throws DomainException {
		//LONGITUD MAYOR
		String user_pass="pepito9%67Afy7nc53336";
		u.setUser_pass(user_pass);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_pass8() throws DomainException {
		//NULL
		String user_pass=null;
		u.setUser_pass(user_pass);
	}
	@Test
	public void testSetUser_tipo() throws DomainException{
		int user_tipo=3;
		u.setUser_tipo(user_tipo);
		assertTrue(u.getUser_tipo()==user_tipo);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_tipo2() throws DomainException{
		//TIPO NO EXISTENTE
		int user_tipo=5;
		u.setUser_tipo(user_tipo);
		assertTrue(u.getUser_tipo()==user_tipo);
	}
	
	@Test
	public void testSetUser_dni() throws DomainException {
		String user_dni="45.855.430-P";
		u.setUser_dni(user_dni);
		assertTrue(u.getUser_dni().equals(user_dni));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_dni2() throws DomainException {
		//LETRA ERRONEA
		String user_dni="45.855.430-D";
		u.setUser_dni(user_dni);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_dni3() throws DomainException {
		//LONGITUD MENOR
		String user_dni="45.855.43-P";
		u.setUser_dni(user_dni);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_dni4() throws DomainException {
		//LONGITUD MAYOR
		String user_dni="45.855.4305-P";
		u.setUser_dni(user_dni);
		assertTrue("Longitud mayor",u.getUser_dni().equals(user_dni));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_dni5() throws DomainException {
		//NULL
		String user_dni=null;
		u.setUser_dni(user_dni);
	}

	@Test
	public void testSetUser_fecAltaCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		u.setUser_fecAlta(cal);
		assertTrue("Fecha hoy",u.getUser_fecAlta().equals(cal));
	}
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaCalendar2() throws DomainException {
		//FECGA DE MAÑANA
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		u.setUser_fecAlta(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaCalendar3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		u.setUser_fecAlta(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaCalendar4() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		u.setUser_fecAlta(cal);
	}

	@Test
	public void testSetUser_fecAltaString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecAlta(date);
		assertTrue("Fecha hoy",TraductorCalendar.CalendarToString(u.getUser_fecAlta()).equals(date));
	}
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaString2() throws DomainException {
		//FECHA DE MAÑANA
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);//Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecAlta(date);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaString3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecAlta(date);
	}
	@Test(expected=DomainException.class)
	public void testSetUser_fecAltaString4() throws DomainException {
		//NULL
		String date= null;
		u.setUser_fecAlta(date);
	}
	@Test
	public void testSetUser_fecConfirmacionCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		u.setUser_fecConfirmacion(cal);
		assertTrue("Fecha hoy",u.getUser_fecConfirmacion().equals(cal));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionCalendar2() throws DomainException {
		//FECHA DE MAÑANA
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		u.setUser_fecConfirmacion(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionCalendar3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);//Un dia menos
		u.setUser_fecConfirmacion(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionCalendar4() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		u.setUser_fecConfirmacion(cal);
	}

	@Test
	public void testSetUser_fecConfirmacionString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecConfirmacion(date);
		assertTrue("Fecha hoy",TraductorCalendar.CalendarToString(u.getUser_fecConfirmacion()).equals(date));
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionString2() throws DomainException {
		//FECHA DE MAÑANA
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);//Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecConfirmacion(date);
	}
	
	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionString3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		u.setUser_fecConfirmacion(date);
	}

	@Test(expected=DomainException.class)
	public void testSetUser_fecConfirmacionString4() throws DomainException {
		//NULL
		String date= null;
		u.setUser_fecConfirmacion(date);
	}
	@Test
	public void testSetUser_pago() {
		Direccion user_pago= new Direccion();
		u.setUser_pago(user_pago);
		assertTrue(u.getUser_pago().equals(user_pago));
	}

	@Test
	public void testSetUser_envio() {
		Direccion user_envio=  new Direccion();
		u.setUser_envio(user_envio);
		assertTrue(u.getUser_envio().equals(user_envio));
	}

}
