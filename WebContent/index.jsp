<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenidos a la Tienda</title>
</head>
<style>
body {
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}

.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
<body class="container">
	<div class="container">
	<h1>Bienvenido a la tienda! v2 <%=(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)?"alumno":"system" %></h1>
	<%
		if(session.getAttribute("user")!=null && session.getAttribute("idUser")!=null){
			response.sendRedirect("inicio.jsp");
		}
	%>
	<hr>
	<form action="Usuario?orden=login" method="post" class="form-signin">
		Email: <input type="text" name="email" class="form-control"><br>
		Password:<input type="password" name="password" class="form-control"><br>
		<input type="submit" value="Enviar" class="btn btn-primary">
		<a id="btnregistrarse" href="registrarse.jsp" class="btn btn-default">Registrarse</a>
	</form>
		<% 
		if(session.getAttribute("ErrorLogin")!=null){
			String message= session.getAttribute("ErrorLogin").toString();
			session.removeAttribute("ErrorLogin");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
			  message+"</div>");
		}
		
	%>
	<hr>
	<div>
	<a href="Categoria" id= "btnCategoria" class="btn btn-primary">Gestion de Categorías</a>
	</div>
	<div>
		<hr>
		<a href="Producto" id= "btnProducto" class="btn btn-primary">Gestion de Productos</a>
	</div>
</body>
</html>
