<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" language="java" %>
<%@ page import="es.rf.tienda.dominio.Categoria" language="java" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado Categorias</title>
</head>
<body>
	<div class="container">
	<h1>Listado de Categorias</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<table class="table">
	<tr>
	    <th>Identificador</th>
	     <th>Nombre</th> 
	    <th>Eliminar</th>
   	</tr>
	<%
	List<Categoria> lista = (List)session.getAttribute("listado");
	for(int i=0; i<lista.size();i++){
		out.println("<tr>");
		out.println("<td id=\"tablecat\">"+lista.get(i).getId_categoria() +"</td>");
		out.println("<td><a href=Categoria?orden=leerCat&id_cat="+lista.get(i).getId_categoria()+" id=\"cat"+i+"\">"+lista.get(i).getCat_nombre()+"</a></td>");
		out.println("<td><a href=Categoria?orden=leerCatElim&id_cat="+lista.get(i).getId_categoria()+" id=\"elimCat"+i+"\" class=\"btn btn-danger\">Eliminar</a></td>");
		out.println("</tr>");
	}
	%>
	</table>
	<a href="nuevaCategoria.jsp" id="btnNuevaCat" class="btn btn-primary">Nueva Categoria</a>
	</div>
</body>
</html>
