package es.rf.tienda.util;

public class MontaSQL {
	public static String addToSalida(String salida,String nombreAtributo, int valor, String separador){
		return addToSalidaSencilla(salida, nombreAtributo, ""+valor, separador);
	}
	
	public static String addToSalida(String salida,String nombreAtributo, long valor, String separador){
		return addToSalidaSencilla(salida, nombreAtributo, ""+valor, separador);
	}
	
	public static String addToSalida(String salida,String nombreAtributo, double valor, String separador){
		return addToSalidaSencilla(salida, nombreAtributo, ""+valor, separador);
	}
	
	public static String addToSalida(String salida,String nombreAtributo, char valor, String separador){
		return addToSalidaSencilla(salida, nombreAtributo, "'"+valor+"'", separador);
	}
	
	public static String addToSalida(String salida,String nombreAtributo, String valor, String separador){
		if(valor!=null){
			return addToSalidaSencilla(salida, nombreAtributo, "'"+valor+"'", separador);
		}
		else{
			return addToSalidaSencilla(salida, nombreAtributo, ""+valor, separador); 
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: addToSalidaSencilla *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite concatenar los atributos para poder completar una sentencia SQL
		 * 
		 * @param salida
		 *         	String La sentencia SQL concatenada hasta el momento
		 * @param nombreAtributo
		 * 			String El nombre de la columna del atributo
		 * @param valor
		 * 			String El valor del atributo especificado
		 * @param separador
		 * 			String El tipo de separador de la operacion SQL	 
		 * @return Un String con la sentencia concatenada hasta el momento mas los parámetros recibidos.
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static String addToSalidaSencilla(String salida,String nombreAtributo, String valor, String separador){
		if(salida!=""){
			salida+= " "+separador+" ";
		}
		if(nombreAtributo!=null){ //CASO SET AND WHERE
			salida+= nombreAtributo+ " = "+ valor +"";
		}
		else{ //CASO DE VALUE (VALOR,VALOR,VALOR)
			salida+= valor;
		}
		return salida;
	}
}
