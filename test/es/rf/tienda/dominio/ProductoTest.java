package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.TraductorCalendar;

public class ProductoTest {
	
	Producto p;
	@Before 
	public void inicio(){
		p= new Producto();
	}
	/*@Test
	public void testEsValido() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSetId_producto() throws DomainException {
		String id_producto="12a45";
		p.setId_producto(id_producto);
		assertTrue(p.getId_producto().equals(id_producto));
	}
	@Test(expected=DomainException.class)
	public void testSetId_producto2() throws DomainException {
		//LONGITUD MENOR
		String id_producto="1234";
		p.setId_producto(id_producto);
	}
	@Test(expected=DomainException.class)
	public void testSetId_producto3() throws DomainException {
		//LONGITUD MAYOR
		String id_producto="123456";
		p.setId_producto(id_producto);
	}
	@Test(expected=DomainException.class)
	public void testSetId_producto4() throws DomainException {
		//NO ALFANUMERICOS
		String id_producto="123%&";
		p.setId_producto(id_producto);
	}
	
	@Test(expected=DomainException.class)
	public void testSetId_producto5() throws DomainException {
		//NULL
		String id_producto=null;
		p.setId_producto(id_producto);
	}

	@Test
	public void testSetPro_descripcion() throws DomainException {
		String pro_descripcion="Descripcion del producto";
		p.setPro_descripcion(pro_descripcion);
		assertTrue(p.getPro_descripcion().equals(pro_descripcion));
	}
	@Test(expected=DomainException.class)
	public void testSetPro_descripcion2() throws DomainException {
		//LONGITUD MENOR
		String pro_descripcion="Desc";
		p.setPro_descripcion(pro_descripcion);
	}
	@Test(expected=DomainException.class)
	public void testSetPro_descripcion3() throws DomainException {
		//LONGITUD MAYOR
		String pro_descripcion="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. ";
		p.setPro_descripcion(pro_descripcion);
	}
	@Test(expected=DomainException.class)
	public void testSetPro_descripcion4() throws DomainException {
		//NO ALFANUMERICOS
		String pro_descripcion="No alfa&/%%";
		p.setPro_descripcion(pro_descripcion);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_descripcion5() throws DomainException {
		//NULL
		String pro_descripcion=null;
		p.setPro_descripcion(pro_descripcion);
	}
	@Test
	public void testSetPro_desLarga() throws DomainException {
		String pro_desLarga="Descripcion larga";
		p.setPro_desLarga(pro_desLarga);
		assertTrue(p.getPro_desLarga().equals(pro_desLarga));
	}
	@Test(expected=DomainException.class)
	public void testSetPro_desLarga2() throws DomainException {
		//LONGITUD MENOR
		String pro_desLarga="Desc";
		p.setPro_desLarga(pro_desLarga);
	}
	@Test(expected=DomainException.class)
	public void testSetPro_desLarga3() throws DomainException {
		//LONGITUD MAYOR
		String pro_desLarga="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. ";
		p.setPro_desLarga(pro_desLarga);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_desLarga4() throws DomainException {
		//NO ALFANUMERICOS
		String pro_desLarga="Descripcion &/%";
		p.setPro_desLarga(pro_desLarga);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_desLarga5() throws DomainException {
		//NULL
		String pro_desLarga=null;
		p.setPro_desLarga(pro_desLarga);
	}
	
	@Test
	public void testSetPro_precio() throws DomainException {
		double pro_precio=40.67d;
		p.setPro_precio(pro_precio);
		assertTrue(p.getPro_precio()==pro_precio);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_precio2() throws DomainException {
		//MENOR O IGUAL QUE ZERO
		double pro_precio=0.00d;
		p.setPro_precio(pro_precio);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_precio3() throws DomainException {
		//RANGO MAYOR
		double pro_precio=101.00d;
		p.setPro_precio(pro_precio);
	}

	@Test
	public void testSetPro_stock() throws DomainException {
		int pro_stock=4;
		p.setPro_stock(pro_stock);
		assertTrue(p.getPro_stock()==pro_stock);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_stock2() throws DomainException {
		//NEGATIVO
		int pro_stock=-1;
		p.setPro_stock(pro_stock);
	}
	
	@Test
	public void testSetPro_fecReposCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		p.setPro_fecRepos(cal);
		assertTrue("Fecha hoy", p.getPro_fecRepos().equals(cal));	
	}
	
	@Test
	public void testSetPro_fecReposCalendar2() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		p.setPro_fecRepos(cal);
		assertTrue("Fecha mayor a la de hoy", p.getPro_fecRepos().equals(cal));	
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecReposCalendar3() throws DomainException {
		//FECHA MENOR A LA DE HOY
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		p.setPro_fecRepos(cal);
	}
	@Test(expected=DomainException.class)
	public void testSetPro_fecReposCalendar4() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		p.setPro_fecRepos(cal);	
	}
	@Test
	public void testSetPro_fecReposString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecRepos(date);
		assertTrue("Fecha hoy",TraductorCalendar.CalendarToString(p.getPro_fecRepos()).equals(date));
	}
	@Test
	public void testSetPro_fecReposString2() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecRepos(date);
		assertTrue("Fecha mañana",TraductorCalendar.CalendarToString(p.getPro_fecRepos()).equals(date));
	}
	@Test(expected=DomainException.class)
	public void testSetPro_fecReposString3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecRepos(date);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecReposString4() throws DomainException {
		//NULL
		String date= null;
		p.setPro_fecRepos(date);
	}

	@Test
	public void testSetPro_fecActiCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		p.setPro_fecActi(cal);
		assertTrue("Fecha hoy",p.getPro_fecActi().equals(cal));	
	}
	
	@Test
	public void testSetPro_fecActiCalendar2() throws DomainException {
		//FECHA MAÑANA
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		p.setPro_fecActi(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecActiCalendar3() throws DomainException {
		//FECHA AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); 
		p.setPro_fecActi(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecActiCalendar4() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		p.setPro_fecActi(cal);
	}
	
	@Test
	public void testSetPro_fecActiString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecActi(date);
		assertTrue("Fecha hoy",TraductorCalendar.CalendarToString(p.getPro_fecActi()).equals(date));
	}
	
	@Test
	public void testSetPro_fecActiString2() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecActi(date);
		assertTrue("Fecha mañana",TraductorCalendar.CalendarToString(p.getPro_fecActi()).equals(date));
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecActiString3() throws DomainException {
		//FECHA MENOR
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecActi(date);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecActiString4() throws DomainException {
		//NULL
		String date= null;
		p.setPro_fecActi(date);
	}

	@Test
	public void testSetPro_fecDesactiCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		p.setPro_fecDesacti(cal);
		assertTrue("Fecha hoy",p.getPro_fecDesacti().equals(cal));	
	}
	
	@Test
	public void testSetPro_fecDesactiCalendar2() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		p.setPro_fecDesacti(cal);
		assertTrue("Fecha mañana",p.getPro_fecDesacti().equals(cal));	
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiCalendar3() throws DomainException {
		//FECHA AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		p.setPro_fecDesacti(cal);
	}
	
	@Test
	public void testSetPro_fecDesactiCalendar4() throws DomainException {
		Calendar cal= Calendar.getInstance();
		p.setPro_fecActi(cal);
		p.setPro_fecDesacti(cal);
		assertTrue("Fecha activacion igual",p.getPro_fecDesacti().equals(cal));	
	}
	@Test
	public void testSetPro_fecDesactiCalendar5() throws DomainException {
		Calendar cal= Calendar.getInstance();
		p.setPro_fecActi(cal);
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		p.setPro_fecDesacti(cal);
		assertTrue("Fecha activacion y fecha desactivacion un dia mas",p.getPro_fecDesacti().equals(cal));	
	}
	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiCalendar6() throws DomainException {
		//FECHA ACTIVACION Y FECHA DESACTIVACION UN DIA MENOS
		Calendar cal2= Calendar.getInstance();
		p.setPro_fecActi(cal2);
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1); //Un dia menos
		p.setPro_fecDesacti(cal);
	}

	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiCalendar7() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		p.setPro_fecDesacti(cal);
	}
	
	@Test
	public void testSetPro_fecDesactiString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecDesacti(date);
		assertTrue("Fecha hoy",TraductorCalendar.CalendarToString(p.getPro_fecDesacti()).equals(date));
	}
	
	@Test
	public void testSetPro_fecDesactiString2() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);//Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecDesacti(date);
		assertTrue("Fecha mañana",TraductorCalendar.CalendarToString(p.getPro_fecDesacti()).equals(date));
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiString3() throws DomainException {
		//FECHA DE AYER
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);//Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecDesacti(date);
	}
	
	@Test
	public void testSetPro_fecDesactiString4() throws DomainException {
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		p.setPro_fecActi(date);
		p.setPro_fecDesacti(date);
		assertTrue("Fecha activacion igual",TraductorCalendar.CalendarToString(p.getPro_fecDesacti()).equals(date));
	}
	
	@Test
	public void testSetPro_fecDesactiString5() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1); //Un dia mas
		String date= TraductorCalendar.CalendarToString(cal);
		Calendar cal2= Calendar.getInstance();
		String date2= TraductorCalendar.CalendarToString(cal2);
		p.setPro_fecActi(date2);
		p.setPro_fecDesacti(date);
		assertTrue("Fecha activacion y desactivacion un dia mas",TraductorCalendar.CalendarToString(p.getPro_fecDesacti()).equals(date));
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiString6() throws DomainException {
		//FECHA ACTIVACION Y DESACTIVACION UN DIA MENOS
		Calendar cal= Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);//Un dia menos
		String date= TraductorCalendar.CalendarToString(cal);
		Calendar cal2= Calendar.getInstance();
		String date2= TraductorCalendar.CalendarToString(cal2);
		p.setPro_fecActi(date2);
		p.setPro_fecDesacti(date);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_fecDesactiString7() throws DomainException {
		//NULL
		String date= null;
		p.setPro_fecDesacti(date);
	}

	@Test
	public void testSetPro_uniVenta() throws DomainException {
		String pro_uniVenta= "UniVent";
		p.setPro_uniVenta(pro_uniVenta);
		assertTrue(p.getPro_uniVenta().equals(pro_uniVenta));
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_uniVenta2() throws DomainException {
		//LONGITUD MAYOR
		String pro_uniVenta= "Uni456789012";
		p.setPro_uniVenta(pro_uniVenta);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_uniVenta3() throws DomainException {
		//NO ALFANUMERICOS
		String pro_uniVenta= "Uni4%&";
		p.setPro_uniVenta(pro_uniVenta);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_uniVenta4() throws DomainException {
		//NULL
		String pro_uniVenta= null;
		p.setPro_uniVenta(pro_uniVenta);
	}

	@Test
	public void testSetPro_cantXUniVenta() throws DomainException {
		double pro_cantXUniVenta=4.05d;
		p.setPro_cantXUniVenta(pro_cantXUniVenta);
		assertTrue(p.getPro_cantXUniVenta()==pro_cantXUniVenta);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_cantXUniVenta2() throws DomainException {
		//NEGATIVO
		double pro_cantXUniVenta=-10.00d;
		p.setPro_cantXUniVenta(pro_cantXUniVenta);
	}

	@Test
	public void testSetPro_uniUltNivel() throws DomainException {
		String pro_uniUltNivel="UniUltNiv";
		p.setPro_uniUltNivel(pro_uniUltNivel);
		assertTrue(p.getPro_uniUltNivel().equals(pro_uniUltNivel));
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_uniUltNivel2() throws DomainException {
		//NO ALFANUMERICOS
		String pro_uniUltNivel="UniUltNiv&/";
		p.setPro_uniUltNivel(pro_uniUltNivel);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_uniUltNivel3() throws DomainException {
		//NULL
		String pro_uniUltNivel=null;
		p.setPro_uniUltNivel(pro_uniUltNivel);
	}

	@Test
	public void testSetId_pais() {
		int id_pais=4;
		p.setId_pais(id_pais);
		assertTrue(p.getId_pais()==id_pais);
	}

	@Test
	public void testSetPro_usoRecomendado() throws DomainException {
		String pro_usoRecomendado="Uso recomendado";
		p.setPro_usoRecomendado(pro_usoRecomendado);
		assertTrue(p.getPro_usoRecomendado().equals(pro_usoRecomendado));
	}
	@Test(expected=DomainException.class)
	public void testSetPro_usoRecomendado2() throws DomainException {
		//NO ALFANUMERICOS
		String pro_usoRecomendado="Uso recomendado&%";
		p.setPro_usoRecomendado(pro_usoRecomendado);
	}
	@Test(expected=DomainException.class)
	public void testSetPro_usoRecomendado3() throws DomainException {
		//LONGITUD MAYOR
		String pro_usoRecomendado="Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. "
				+ "Sed tempus, urna in mattis sodales, nibh elit suscipit mauris, in "
				+ "porta lacus mi quis tellus. Suspendisse sit amet aliquam ligula, ut. ";
		p.setPro_usoRecomendado(pro_usoRecomendado);
	}

	@Test(expected=DomainException.class)
	public void testSetPro_usoRecomendado4() throws DomainException {
		//NULL
		String pro_usoRecomendado=null;
		p.setPro_usoRecomendado(pro_usoRecomendado);
	}
	
	@Test
	public void testSetId_categoria() throws DomainException {
		int id_categoria=6;
		p.setId_categoria(id_categoria);
		assertTrue(p.getId_categoria()==id_categoria);
	}
	
	@Test(expected=DomainException.class)
	public void testSetId_categoria2() throws DomainException {
		//NEGATIVO
		int id_categoria=-1;
		p.setId_categoria(id_categoria);
	}

	@Test
	public void testSetPro_stkReservado() throws DomainException {
		int pro_stkReservado=5;
		p.setPro_stkReservado(pro_stkReservado);
		assertTrue(p.getPro_stkReservado()==pro_stkReservado);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_stkReservado2() throws DomainException {
		//NEGATIVO
		int pro_stkReservado=-1;
		p.setPro_stkReservado(pro_stkReservado);
	}

	@Test
	public void testSetPro_nStkAlto() throws DomainException {
		int pro_nStkAlto=8;
		p.setPro_nStkAlto(pro_nStkAlto);
		assertTrue(p.getPro_nStkAlto()==pro_nStkAlto);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_nStkAlto2() throws DomainException {
		//NEGATIVO
		int pro_nStkAlto=-1;
		p.setPro_nStkAlto(pro_nStkAlto);
	}

	@Test
	public void testSetPro_nStkBajo() throws DomainException {
		int pro_nStkBajo=4;
		p.setPro_nStkBajo(pro_nStkBajo);
		assertTrue(p.getPro_nStkBajo()==pro_nStkBajo);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_nStkBajo2() throws DomainException {
		//NEGATIVO
		int pro_nStkBajo=-1;
		p.setPro_nStkBajo(pro_nStkBajo);
	}
	
	@Test
	public void testSetPro_nStkBajo3() throws DomainException {
		int pro_nStkAlto=8;
		int pro_nStkBajo=4;
		p.setPro_nStkAlto(pro_nStkAlto);
		p.setPro_nStkBajo(pro_nStkBajo);
		assertTrue("Stock alto mayor que stock bajo",p.getPro_nStkBajo()==pro_nStkBajo);
	}
	
	@Test
	public void testSetPro_nStkBajo4() throws DomainException {
		int pro_nStkAlto=4;
		int pro_nStkBajo=4;
		p.setPro_nStkAlto(pro_nStkAlto);
		p.setPro_nStkBajo(pro_nStkBajo);
		assertTrue("Stock alto igual que stock bajo",p.getPro_nStkBajo()==pro_nStkBajo);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_nStkBajo5() throws DomainException {
		//STOCK ALTO MENOR QUE STOCK BAJO
		int pro_nStkAlto=3;
		int pro_nStkBajo=4;
		p.setPro_nStkAlto(pro_nStkAlto);
		p.setPro_nStkBajo(pro_nStkBajo);
	}
	
	@Test
	public void testSetPro_stat() throws DomainException {
		char pro_stat='A';
		p.setPro_stat(pro_stat);
		assertTrue(p.getPro_stat()==pro_stat);
	}
	
	@Test
	public void testSetPro_stat2() throws DomainException {
		char pro_stat='B';
		p.setPro_stat(pro_stat);
		assertTrue(p.getPro_stat()==pro_stat);
	}
	
	@Test(expected=DomainException.class)
	public void testSetPro_stat3() throws DomainException {
		//NI 'A' NI 'B'
		char pro_stat='C';
		p.setPro_stat(pro_stat);
	}
	

}
