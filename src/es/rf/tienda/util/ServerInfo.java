package es.rf.tienda.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerInfo {
	public String getIPServer() throws UnknownHostException{
		InetAddress IP=InetAddress.getLocalHost();
		return IP.getHostAddress();
	}
	public String getUserDB(String IP){
		if(IP.equals("192.***.***.***")){
			return "alumno";
		}
		else{
			return "system";
		}
	}
	public String getPassDB(String IP){
		if(IP.equals("192.***.***.***")){
			return "*******";
		}
		else{
			return "*****";
		}
	}
}
