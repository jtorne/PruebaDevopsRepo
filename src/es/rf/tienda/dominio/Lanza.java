package es.rf.tienda.dominio;

import java.util.Calendar;

import es.rf.tienda.exception.DomainException;
import es.rf.tienda.util.TraductorCalendar;
import es.rf.tienda.util.Validator;

public class Lanza {

	public static void main(String[] args) throws DomainException {
		Producto p= new Producto();
		p.setId_producto("ID001");
		p.setPro_descripcion("jasfhkjá664");
		p.setPro_desLarga("sjldgj slsgjl s.,. ag.gj lajglg a a.a=");
		p.setPro_stock(5);
		p.setPro_fecRepos(Calendar.getInstance());
		p.setPro_fecActi(Calendar.getInstance());
		p.setPro_fecDesacti(Calendar.getInstance());
		//p.setPro_fecRepos("27-4-2016");
		//p.setPro_fecActi("27-4-2016");
		//p.setPro_fecDesacti("29-4-2016");
		p.setPro_precio(99.46387874637f);
		p.setPro_uniVenta("UniVent");
		p.setPro_cantXUniVenta(843.543);
		p.setPro_uniUltNivel("UniUltNiv");
		p.setId_pais(346);
		p.setPro_usoRecomendado("uso rec");
		p.setId_categoria(5);
		p.setPro_stkReservado(6);
		p.setPro_nStkAlto(9);
		p.setPro_nStkBajo(3);
		p.setPro_stat('B');
		
		Usuario u = new Usuario();
		u.setId_usuario(82365);
		u.setUser_nombre("jordi");
		u.setUser_email("j.torne@mail.com");
		u.setUser_pass("pepito9%67A");
		u.setUser_tipo(9);
		u.setUser_dni("45.855.430-P"); //Formato XX.XXX.XXX-L
		u.setUser_fecAlta(Calendar.getInstance());
		u.setUser_fecConfirmacion(Calendar.getInstance());
		//u.setUser_fecAlta("26-4-2016");
		//u.setUser_fecConfirmacion("26-4-2016");
		
		
		Categoria c= new Categoria();
		c.setId_categoria(1);
		c.setCat_nombre("nombre categoria");
		c.setCat_descripcion("la descripcion de esta categoria");
		
		Direccion d= new Direccion();	
		d.setDir_nombre("Nombre direccion");
		
		Carrito car= new Carrito();
		car.setId_pedido(78683);
		car.setId_usuario(25662);
		car.setId_producto("idproducto");
		car.setCar_tarjeta("1234567890123456");
		car.setCar_nombre("nombre carrito");
		Calendar cal= Calendar.getInstance();
		cal.set(5, cal.get(5)+1);	//Field 5 de Calendar es Day
		car.setCar_feCadud(cal);
		//car.setCar_feCadud("29-4-2016");
		car.setCar_ccv(123);
	
		
	}
}
