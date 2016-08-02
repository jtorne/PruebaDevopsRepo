<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar</title>
</head>
<body class="container">
	<%
		if(session.getAttribute("user")!=null && session.getAttribute("idUser")!=null){
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		}
	%>
	<% 
		String nombre="";
		String email="";
		String dni="";
		if(request.getAttribute("nombre")!=null){
			nombre =request.getAttribute("nombre").toString(); 
		}
		if(request.getAttribute("email")!=null){
			email =request.getAttribute("email").toString(); 
		}
		if(request.getAttribute("dni")!=null){
			dni =request.getAttribute("dni").toString(); 
		}
	%>
	<h1>Registrarse a la Tienda</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<form action="Usuario?orden=registrar" method="post">
		<label>Nombre*: </label><input id="nombre" type="text" name="nombre" value="<%= nombre %>" class="form-control"><br>
		<label>Email*: </label><input id="email" type="email" name="email" value="<%= email %>" class="form-control"><br>
		<label>DNI (Formato:XX.XXX.XXX-A): </label><input id="dni" type="text" name="dni" value="<%= dni %>" class="form-control"><br>
		<label>Password*: </label><input id="password" type="password" name="password" class="form-control"><br>
		<label>Confirmación Password*: </label><input id="passwordConfirmation" type="password" name="passwordConfirmation" class="form-control"><br>
		<input id="submit" type="submit" value="Enviar" class="btn btn-primary">
	</form>
		<% 
		if(session.getAttribute("ErrorRegistro")!=null){
			String message= session.getAttribute("ErrorRegistro").toString();
			session.removeAttribute("ErrorRegistro");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
			  message+"</div>");
		}
		
	%>
</body>
</html>
