package es.rf.tienda.dominio;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.TraductorCalendar;

public class CarritoTest {

	Carrito car;
	
	@Before 
	public void inicio(){
		car= new Carrito();
	}
	
	/*@Test
	public void testEsValido() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSetId_pedido() throws DomainException {
		int id_pedido= 1234;
		car.setId_pedido(id_pedido);
		assertTrue(car.getId_pedido()==id_pedido);
	}
	@Test(expected=DomainException.class)
	public void testSetId_pedido2() throws DomainException {
		//"Negativo"
		int id_pedido= -1234;
		car.setId_pedido(id_pedido);
	}

	@Test
	public void testSetId_usuario() throws DomainException {
		int id_usuario=4321;
		car.setId_usuario(id_usuario);
		assertTrue(car.getId_usuario()==id_usuario);
	}
	@Test(expected=DomainException.class)
	public void testSetId_usuario2() throws DomainException {
		//Negativo
		int id_usuario=-4321;
		car.setId_usuario(id_usuario);
	}

	@Test
	public void testSetId_producto() throws DomainException {
		String id_producto="idProduct1";
		car.setId_producto(id_producto);
		assertTrue(car.getId_producto().equals(id_producto));
	}
	
	@Test(expected=DomainException.class)
	public void testSetId_producto2() throws DomainException {
		//No alfanumericos
		String id_producto="idProduct%$1";
		car.setId_producto(id_producto);
	}

	@Test(expected=DomainException.class)
	public void testSetId_producto3() throws DomainException {
		//NULL
		String id_producto=null;
		car.setId_producto(id_producto);
	}
	
	@Test
	public void testSetCar_cantidad() throws DomainException {
		int car_cantidad=4;
		car.setCar_cantidad(car_cantidad);
		assertTrue(car.getCar_cantidad()==car_cantidad);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_cantidad2() throws DomainException {
		//MENOR O IGUAL QUE 0
		int car_cantidad=0;
		car.setCar_cantidad(car_cantidad);
	}

	@Test
	public void testSetCar_precio() throws DomainException {
		double car_precio=45.04d;
		car.setCar_precio(car_precio);
		assertTrue(car.getCar_precio()==car_precio);
	}
	@Test(expected=DomainException.class)
	public void testSetCar_precio2() throws DomainException {
		//MENOR O IGUAL QUE 0
		double car_precio=0d;
		car.setCar_precio(car_precio);
	}

	@Test
	public void testSetCar_envio() {
		Direccion car_envio= new Direccion();
		car.setCar_envio(car_envio);
		assertTrue(car.getCar_envio()==car_envio);
	}

	@Test
	public void testSetCar_pago() {
		Direccion car_pago= new Direccion();
		car.setCar_pago(car_pago);
		assertTrue(car.getCar_pago()==car_pago);
	}

	@Test
	public void testSetCar_tarjeta() throws DomainException {
		String car_tarjeta="1234567890123456";
		car.setCar_tarjeta(car_tarjeta);
		assertTrue(car.getCar_tarjeta().equals(car_tarjeta));
	}
	@Test(expected=DomainException.class)
	public void testSetCar_tarjeta2() throws DomainException {
		//LONGITUD MENOR
		String car_tarjeta="123456789012345";
		car.setCar_tarjeta(car_tarjeta);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_tarjeta3() throws DomainException {
		//NULL
		String car_tarjeta=null;
		car.setCar_tarjeta(car_tarjeta);
	}
	@Test
	public void testSetCar_feCadudCalendar() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.set(5, cal.get(5)+1);
		car.setCar_feCadud(cal);
		assertTrue(car.getCar_feCadud().equals(cal));
		assertTrue(car.getCar_feCadud().compareTo(cal)==0);
	}
	@Test(expected=DomainException.class)
	public void testSetCar_feCadudCalendar2() throws DomainException {
		//FECHA ACTUAL
		Calendar cal= Calendar.getInstance();
		car.setCar_feCadud(cal);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_feCadudCalendar3() throws DomainException {
		//NULL
		Calendar cal= Calendar.getInstance();
		cal=null;
		car.setCar_feCadud(cal);
	}

	@Test
	public void testSetCar_feCadudString() throws DomainException {
		Calendar cal= Calendar.getInstance();
		cal.set(5, cal.get(5)+3);
		String date= TraductorCalendar.CalendarToString(cal);
		car.setCar_feCadud(date);
		assertTrue(TraductorCalendar.CalendarToString(car.getCar_feCadud()).equals(date));
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_feCadudString2() throws DomainException {
		//FECHA ACTUAL
		Calendar cal= Calendar.getInstance();
		String date= TraductorCalendar.CalendarToString(cal);
		car.setCar_feCadud(date);
	}
	@Test(expected=DomainException.class)
	public void testSetCar_feCadudString3() throws DomainException {
		//NULL
		String date= null;
		car.setCar_feCadud(date);
	}

	@Test
	public void testSetCar_ccv() throws DomainException {
		int car_ccv=123;
		car.setCar_ccv(car_ccv);
		assertTrue(car.getCar_ccv()==car_ccv);
	}
	@Test(expected=DomainException.class)
	public void testSetCar_ccv2() throws DomainException {
		//MENOR
		int car_ccv=99;
		car.setCar_ccv(car_ccv);
	}
	@Test(expected=DomainException.class)
	public void testSetCar_ccv3() throws DomainException {
		//MAYOR
		int car_ccv=1000;
		car.setCar_ccv(car_ccv);
	}
	
	@Test
	public void testSetCar_nombre() throws DomainException {
		String car_nombre= "pepito";
		car.setCar_nombre(car_nombre);
		assertTrue(car.getCar_nombre().equals(car_nombre));
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_nombre2() throws DomainException {
		//NO ALFANUMERICOS
		String car_nombre= "pepito%&";
		car.setCar_nombre(car_nombre);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_nombre3() throws DomainException {
		//NULL
		String car_nombre= null;
		car.setCar_nombre(car_nombre);
	}

	@Test
	public void testSetCar_stat() throws DomainException {
		int car_stat=5;
		car.setCar_stat(car_stat);
		assertTrue(car.getCar_stat()==car_stat);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_stat2() throws DomainException {
		//NO ES UN ESTADO DE PEDIDO
		int car_stat=8;
		car.setCar_stat(car_stat);
	}

	@Test
	public void testSetCar_FeCambio() throws DomainException {
		String[] car_FeCambio= {"21-4-2016","23-4-2016","27-4-2016"};
		car.setCar_FeCambio(car_FeCambio);
		assertTrue(car.getCar_FeCambio()==car_FeCambio);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_FeCambio2() throws DomainException {
		//ALGUNA FECHA NO VALIDA
		String[] car_FeCambio= {"21-4-2016","29-2-2017","27-4-2016"};
		car.setCar_FeCambio(car_FeCambio);
	}
	
	@Test(expected=DomainException.class)
	public void testSetCar_FeCambio3() throws DomainException {
		//NULL
		String[] car_FeCambio= null;
		car.setCar_FeCambio(car_FeCambio);
	}

}
