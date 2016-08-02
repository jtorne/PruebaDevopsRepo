package es.rf.tienda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/********************************************************************************************
 * NOMBRE: Validator.java
 * 
 * DESCRIPCION: Clase auxiliar para validar los datos que sean introducidos en
 * la aplicaci√©n.
 * 
 * @version 29/04/2016
 * @author Jordi TornÈ
 * 
 ******************************************************************************************/
public class Validator {

	/**
	 * PatrÛn para validar el password, tiene que contener al menos una min˙scula, may˙scula
	 * car·cter especial, numero y de longitud entre 6 y 20.
	 */
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	/**
	 * Patr√≥n para validar el email, evitando puntos finales antes de la @ y que
	 * solo contenga car√©cteres alfanum√©ricos
	 */
	private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Permite verificar que un DNI cumple con el patr√©n XX.XXX.XXX-L
	 */
	private final static String DNI_PATTERN = "\\d{2}\\.\\d{3}\\.\\d{3}-[a-zA-Z]";

	/**
	 * Permite validar un tel√©fono, el cual debe contener de 10 a 20 d√©gitos y
	 * donde los espacios est√©n permitidos
	 */
	private final static String PHONE_PATTERN = "[\\d ]{10,20}";

	/**
	 * Orden de las letras con las cuales se comprobar√© la validez del DNI
	 */
	private final static String LETRA_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

	/**
	 * Longitud que debe tener todo DNI pasado a la aplicaci√©n.
	 */
	private final static int LONGITUD_DNI = 12; 
	
	/**
	 * Permite validar que contenga alfanumÈricos 
	 * donde los espacios, puntos y comas est√©n permitidos
	 */
	private final static String ALFANUMERICOS= "^[a-zA-Z0-9ÒÁ\\s,.:-·ÈÌÛ˙‡ËÏÚ˘Ô¸]*$"; // \\s: los espacios

	/*
	 * *************************************************************************
	 * ************** NOMBRE: isAlfanumeric *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Permite verificar que el texto pasado solo contiene caracters
		 * alfanum√©ricos
		 * 
		 * @param texto
		 *            String a verificar que solo tenga car√©cteres alfanum√©ricos
		 * 
		 * @return true, si cumple solo contiene caracters alfanum√©ricos. <br>
		 *         false en caso contrario FECHA: Abril 2016
		 * 
		 *         AUTOR: Jordi TornÈ- Barcelona
		 * 
		 **************************************************************************************/
	public static boolean isAlfanumeric(String texto) {
		if(texto!=null){
			Pattern alfaNumericPattern= Pattern.compile(ALFANUMERICOS);
			Matcher m= alfaNumericPattern.matcher(texto);
			return m.matches();
		}
		else{
			return false;
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: isVacio *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Permite verificar que el String esta vacio
		 * 
		 * @param prueba
		 *            String a verificar que este vacio
		 * 
		 * @return true, si es un String vacia <br>
		 *         false en caso contrario FECHA: Abril 2016
		 * 
		 *         AUTOR: Jordi TornÈ - Barcelona
		 * 
		 **************************************************************************************/
	public static boolean isVacio(final String prueba) {
		if(prueba!=null){
			return prueba.isEmpty();
		}
		else{
			return false;
		}
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumplePhoneNumber *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * El phone number debe tener un total de entre 10 y 20, contando
		 * d√©gitos y espacios. M√©nimo aceptable son 10 d√©gitos.
		 * 
		 * @param phoneNumber
		 *            String con el n√©mero de telefono de entre 10 y 20 d√©gitos.
		 *            Puede tener espacios, pero siempre con 10 d√©gitos como
		 *            m√©nimo.
		 * 
		 * @return true, si cumple todas las condiciones
		 */
	/*
	 * FECHA: Abril 2016 AUTOR: Jordi TornÈ
	 * 
	 **************************************************************************************/
	public static boolean cumplePhoneNumber(String phoneNumber) {
		if(phoneNumber!=null){
			Pattern phoneNumberPattern= Pattern.compile(PHONE_PATTERN);
			Matcher m= phoneNumberPattern.matcher(phoneNumber);
			return m.matches();
		}
		else{
			return false;
		}
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: isEmailValido *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba si el email tiene un formato que pueda ser v√©lido.
		 * 
		 * @param email
		 *            String a comprobar
		 * 
		 * @return true, en caso que el formato sea v√©lido FECHA: Abril 2016
		 * 
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean isEmailValido(String email) {
		if(email!=null){
			Pattern emailPattern= Pattern.compile(EMAIL_PATTERN);
			Matcher m= emailPattern.matcher(email);
			return m.matches();
		}
		else{
			return false;
		}
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleDNI *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Esta funci√©n verifica que el DNI cumple el siguiente formato:
		 * xx.xxx.xxx-L <br>
		 * El DNI ha de tener longitud 12
		 * 
		 * @param dni
		 *            String con DNI a ser validado
		 * 
		 * @return true, si el DNI cumple el estandar nacional. FECHA: Abril
		 *         2016 AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean cumpleDNI(String dni) {
		if(dni!=null){
			if(dni.length()==LONGITUD_DNI){
				Pattern dniPattern= Pattern.compile(DNI_PATTERN);
				Matcher m= dniPattern.matcher(dni);
				if(m.matches()){
					dni=dni.replace(".", "");
					dni=dni.replace("-", "");
					char letra= dni.charAt(8);
					dni= dni.substring(0,8);
					int d= Integer.parseInt(dni);
					d= d%23;
					char reference= LETRA_DNI.charAt(d);
					if(reference==letra){
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}

	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleRango *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que un n√©mero se encuentra entre 2 valores
		 * 
		 * @param valor
		 *            (int) N√©mero a comprobar
		 * @param valorMinimo
		 *            (int) M√©nimo valor aceptable
		 * @param valorMaximo
		 *            (int) M√©ximo valor aceptable
		 * 
		 * @return true si valorMinimo <= valor <= valorMaximo FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean cumpleRango(int valor, int valorMinimo, int valorMaximo) {
		return (valor>=valorMinimo && valor<=valorMaximo);
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleRangoMin *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que un n√©mero es mayor o igual que otro
		 * 
		 * @param valor
		 *            (int) N√©mero a comprobar
		 * @param valorMinimo
		 *            (int) M√©nimo valor aceptable
		 * 
		 * @return true si valorMinimo <= valor FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean cumpleRangoMin(int valor, int valorMinimo){
		return (valor>=valorMinimo);
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleLongitudMin *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprobar que el texto pasado tiene una longitud de al menos x
		 * caracteres, siendo x el entero pasado como par√©metro
		 * 
		 * @param texto
		 *            String texto a comprobar
		 * @param longitudMinima
		 *            int que indique longitud m√©nima.
		 * 
		 * @return cierto, si la longitud del texto es mayor o igual que
		 *         longitudMinima FECHA: Abril 2016 AUTOR: Jordi TornÈ 
		 * 
		 **************************************************************************************/
	public static boolean cumpleLongitudMin(String texto, int longitudMinima) {
		if(texto!=null)
			return (texto.length()>=longitudMinima);
		else
			return false;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleLongitudMax *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprobar que el texto pasado tiene una longitud de, como mucho, x
		 * caracteres, siendo x el entero pasado como par√©metro
		 * 
		 * @param texto
		 *            String con el texto a comprobar
		 * @param longitudMaxima
		 *            int con la longitud m√©xima del texto
		 * 
		 * @return true, si el texto es menor o igual que la longitud m√©xima.
		 *         FECHA: Abril 2016 AUTOR: Jordi TornÈ 
		 * 
		 **************************************************************************************/
	public static boolean cumpleLongitudMax(String texto, int longitudMaxima) {
		if(texto!=null)
			return (texto.length()<=longitudMaxima);
		else
			return false;
	}

	/*
	 * *************************************************************************
	 * ************** NOMBRE: cumpleLongitud *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprobar que la longitud de un texto se encuentra entre unos valores
		 * m√©ximos y m√©nimos
		 * 
		 * @param texto
		 *            String con el texto que a validar
		 * @param longitudMinima
		 *            (int) M√©nima longitud del texto
		 * @param longitudMaxima
		 *            (int) M√©xima longitud v√°lida para el texo
		 * 
		 * @return true, si la longitud del texto cumple: longitudMinima <=
		 *         longitudTexto <= longitudMaxima FECHA: Abril 2016 AUTOR:
		 *        	Jordi TornÈ - Barcelona
		 * 
		 **************************************************************************************/
	public static boolean cumpleLongitud(String texto, int longitudMinima, int longitudMaxima) {
		if(texto!=null)
			return (texto.length()>=longitudMinima && texto.length()<=longitudMaxima);
		else
			return false;
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esFechaIgual *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que dos fechas son iguales
		 * 
		 * @param fecha
		 *            (Calendar) Fecha a comprobar
		 * @param fecha2
		 *            (Calendar) Segunda fecha a comprobar
		 * 
		 * @return true si fecha=fecha2 (aÒo,mes y dia iguales sin horas,minutos y segundos) FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean esFechaIgual(Calendar fecha,Calendar fecha2) {
		if(fecha==null || fecha2==null) 
			return false;
		else{
			return (fecha.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) &&
					fecha.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) &&
					fecha.get(Calendar.DAY_OF_MONTH) == fecha2.get(Calendar.DAY_OF_MONTH));
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esFechaMayorQue *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que una fecha es mayor que otra
		 * 
		 * @param fecha
		 *            (Calendar) Fecha a comprobar
		 * @param fechaMinima
		 *            (Calendar) Fecha minima
		 * 
		 * @return true si fecha>fechaMinima FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	
	public static boolean esFechaMayorQue(Calendar fecha, Calendar fechaMinima) {
		if(fecha==null || fechaMinima==null)
			return false;
		else{
			if (fecha.get(Calendar.YEAR) > fechaMinima.get(Calendar.YEAR)) 
		       	return true;
			else if (fecha.get(Calendar.MONTH) > fechaMinima.get(Calendar.MONTH)) 
				return true;
			else {
				return (fecha.get(Calendar.DAY_OF_MONTH) > fechaMinima.get(Calendar.DAY_OF_MONTH));
			}
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esFechaValida *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que una fecha es mayor o igual que otra
		 * 
		 * @param fecha
		 *            (Calendar) Fecha a comprobar
		 * @param fechaMinima
		 *            (Calendar) Fecha minima aceptable
		 * 
		 * @return true si fecha>=fechaMinima FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	
	public static boolean esFechaValida(Calendar fecha, Calendar fechaMinima) {
		if(fecha==null || fechaMinima==null)
			return false;
		else{
			int caso=0;
			if (fecha.get(Calendar.YEAR) != fechaMinima.get(Calendar.YEAR)) 
		        caso+=(fecha.get(Calendar.YEAR) - fechaMinima.get(Calendar.YEAR));
			else if (fecha.get(Calendar.MONTH) != fechaMinima.get(Calendar.MONTH)) 
				caso+=(fecha.get(Calendar.MONTH) - fechaMinima.get(Calendar.MONTH));
			else {
				caso+=(fecha.get(Calendar.DAY_OF_MONTH) - fechaMinima.get(Calendar.DAY_OF_MONTH));
			}
			if(caso>=0){
				return true;
			}
			else {
				return false;
			}
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esFechaValida *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que una fecha es mayor o igual que otra
		 * 
		 * @param fecha
		 *            (String) Fecha a comprobar
		 * @param fechaMinima
		 *            (Calendar) Fecha minima aceptable
		 * 
		 * @return true si fecha>=fechaMinima FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	
	public static boolean esFechaValida(String fecha, Calendar fechaMinima){
		if(fechaMinima!=null && fecha !=null){
			String [] s= fecha.split("-");
			if(s.length==3){
				SimpleDateFormat sdf= new SimpleDateFormat("d-M-yyyy");
				Calendar calendar= Calendar.getInstance();
				calendar.setLenient(true);
				try{
					calendar.setTime(sdf.parse(fecha));
				}
				catch(ParseException e){
					return false;
				}
				if (fecha.equals(sdf.format(calendar.getTime()))){
					return esFechaValida(calendar,fechaMinima);
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esFechaValida *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que una fecha existe y tiene el formato correcto
		 * 
		 * @param fecha
		 *            (String) Fecha a comprobar
		 * 
		 * @return true si fecha existe y tiene el formato correcto 
		 * 			FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	
	public static boolean esFechaValida(String fecha){
		if(fecha !=null){
			String [] s= fecha.split("-");
			if(s.length==3){
				SimpleDateFormat sdf= new SimpleDateFormat("d-M-yyyy");
				Calendar calendar= Calendar.getInstance();
				calendar.setLenient(true);
				try{
					calendar.setTime(sdf.parse(fecha));
				}
				catch(ParseException e){
					return false;
				}
				if (fecha.equals(sdf.format(calendar.getTime()))){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esPasswordValida *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Verifica que el password contiene al menos una min˙scula, una may˙scula, un
		 * car·cter especial, un digito y que tenga una longitud entre 6 y 20. 
		 * @param fecha
		 *            (String) Password a comprobar
		 * 
		 * @return true si el password contiene al menos una min˙scula, una may˙scula, un
		 *        car·cter especial, un digito y que tenga una longitud entre 6 y 20.
		 * 		   FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	
	public static boolean esPasswordValida(String password) {
		if(password!=null){
			Pattern p= Pattern.compile(PASSWORD_PATTERN);
			Matcher m = p.matcher(password);
			return m.matches();
		}
		else
			return false;
	}
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esEstadoValido *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que es un estado valido, siendo 'A' y 'B' los estados validos.
		 * 
		 * @param caracter
		 *            (char) Estado a comprobar
		 * 
		 * @return true si caracter='A' i si caracter='B'
		 * 		   false en caso contrario
		 * 			FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/

	public static boolean esEstadoValido(char caracter){
		return (caracter=='A'||caracter=='B');
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esTipoUsuarioValido *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que es un estado valido, siendo 0,1,2,3,4 y 9 los estados validos.
		 * 
		 * @param tipo
		 *            (int) Tipo de usuario a comprobar
		 * 
		 * @return true si tipo es igual a 0,1,2,3,4 Û 9.
		 * 		   false en caso contrario
		 * 			FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean esTipoUsuarioValido(int tipo){
		return (tipo==0 || tipo==1 || tipo==2 || tipo==3 || tipo==4 || tipo==9);
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: esEstadoPedidoValido *
	 * 
	 * DESCRIPCI√©N:
	 *//**
		 * Comprueba que es un estado de pedido valido, siendo 1,2,3,4,5,6,7 y 9 los estados validos.
		 * 
		 * @param estado
		 *            (int) Estado del pedido
		 * 
		 * @return true si estado es igual a 1,2,3,4,5,6,7 Û 9.
		 * 		   false en caso contrario
		 * 			FECHA: Abril 2016
		 *         AUTOR: Jordi TornÈ
		 * 
		 **************************************************************************************/
	public static boolean esEstadoPedidoValido(int estado){
		return (estado==1 || estado==2 || estado==3 || estado==4 || estado==5 || estado==6 ||
				estado==7 || estado==9);
	}
}
