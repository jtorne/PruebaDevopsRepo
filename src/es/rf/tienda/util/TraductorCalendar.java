package es.rf.tienda.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.rf.tienda.exception.DomainException;

public class TraductorCalendar {
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: CalendarToString *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite transformar una fecha en formato Calendar en una fecha con formato (dd-mm-aaaa)
		 * guardada com String
		 * @param c
		 *            Calendar La fecha que se quiere transformar
		 * @return La fecha transformada en formato (dd-mm-aaaa)
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static String CalendarToString(Calendar c){
		if(c!=null){
			SimpleDateFormat sdf= new SimpleDateFormat("d-M-yyyy");
			return sdf.format(c.getTime());
		}
		else{
			return null;
		}
	}
	
	/*
	 * *************************************************************************
	 * ************** NOMBRE: StringToCalendar *
	 * 
	 * DESCRIPCIÃ©N:
	 *//**
		 * Permite transformar una fecha en formato (dd-mm-aaaa) en una fecha con formato 
		 * Calendar
		 * @param s
		 *            String La fecha que se quiere transformar
		 * @return La fecha transformada en formato Calendar
		 *         
		 *         FECHA: Abril 2016
		 *         AUTOR: Jordi Torné - Barcelona
		 * 
		 **************************************************************************************/
	public static Calendar StringToCalendar(String s) throws DomainException{
		SimpleDateFormat sdf= new SimpleDateFormat("d-M-yyyy");
		Calendar calendar= Calendar.getInstance();
		calendar.setLenient(false);
		try{
			calendar.setTime(sdf.parse(s));
			return calendar;
		}
		catch(ParseException e){
			throw new DomainException(ErrorMessages.ERM_020);
		}
	}
}
