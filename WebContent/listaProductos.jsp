<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" language="java" %>
<%@ page import="es.rf.tienda.dominio.Producto" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Productos</title>
</head>
<body>
	<div class="container">
	<h1>Listado de Productos</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<table class="table">
	<tr>
	    <th>Identificador</th>
	    <th>Descripción</th>
	    <th>Precio</th>  
	    <th>Eliminar</th>
   	</tr>
	<%
	List<Producto> lista = (List)session.getAttribute("listado");
	for(int i=0; i<lista.size();i++){
		out.println("<tr>");
		out.println("<td><a href=Producto?orden=leerProd&id_Prod="+lista.get(i).getId_producto()+" id=\"prod"+i+"\">"+lista.get(i).getId_producto() +"</a></td>");
		out.println("<td>"+lista.get(i).getPro_descripcion()+"</td>");
		out.println("<td>"+lista.get(i).getPro_precio()+" "+lista.get(i).getPro_uniVenta()+"</td>");
		out.println("<td><a href=Producto?orden=leerProdElim&id_Prod="+lista.get(i).getId_producto()+" id=\"elimProd"+i+"\" class=\"btn btn-danger\">Eliminar</a></td>");
		out.println("</tr>");
	}
	%>
	</table>
	<a href="nuevoProducto.jsp" id="btnNuevoProducto" class="btn btn-primary">Nuevo Producto</a>
	</div>
</body>
</html>
