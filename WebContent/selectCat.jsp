<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.rf.tienda.dominio.Categoria" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Categoria</title>
</head>
<body>
	<div class="container">
	<h1>Categoría Seleccionada</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<%
		Categoria c= (Categoria)session.getAttribute("Categoria");
		out.println("<label> Identificador : "+c.getId_categoria()+"</label>");
		String desc="";
		if(c.getCat_descripcion()!=null){
			desc=c.getCat_descripcion();
		}
	%>
	<form action="Categoria?orden=update&id_cat=<%=c.getId_categoria()%>" method="post">
		<label>Nombre: </label><input type="text" name="nombreCat" value="<%=c.getCat_nombre()%>">
		<br>
		<label>Descripción: </label><input type="text" name="descCat" value="<%=desc%>">
		<br>
		<input type="submit" value="Modificar" class="btn btn-primary">
	</form>
	</div>
		<% 
		if(session.getAttribute("ErrorUpdateCat")!=null){
			String message= session.getAttribute("ErrorUpdateCat").toString();
			session.removeAttribute("ErrorUpdateCat");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
					  message+"</div>");
		}
		%>
</body>
</html>