<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva Categoria</title>
</head>
<body>
	<div class="container">
	<h1>Nueva Categoria</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<form action="Categoria?orden=crear" method="post">
		<label>Nombre: </label><input id="nombreCat" type="text" name="nombreCat"><br>
		<label>Descripción: </label><input id="descripcionCat" type="text" name="descripcionCat"><br>
		<input id="submit" type="submit" value="Crear" class="btn btn-primary">
	</form>
	<% 
		if(session.getAttribute("ErrorCreateCat")!=null){
			String message= session.getAttribute("ErrorCreateCat").toString();
			session.removeAttribute("ErrorCreateCat");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
					  message+"</div>");
		}
	%>
	</div>
</body>
</html>
