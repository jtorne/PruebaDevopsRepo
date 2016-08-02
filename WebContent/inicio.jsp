<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio</title>
</head>
<body>

	<%
		if(session.getAttribute("user")==null && session.getAttribute("idUser")==null){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	%>
	<div class="container">
	<h1>Bienvenido al Inicio</h1>
	<hr>
	<% String usuario = session.getAttribute("user").toString();
	out.println("Hola "+usuario);
	%>
	<br>
	<br>
	<br>
	<hr>
	<a href="Usuario?orden=logout">Bye  <%=usuario%></a>
	<hr>
	<a id="borrarse" href="Usuario?orden=borrarse">Borrarse de la web <%=usuario%></a>
	</div>
</body>
</html>
