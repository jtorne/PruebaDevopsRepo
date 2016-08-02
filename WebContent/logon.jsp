<%@page import="es.rf.tienda.dominio.Usuario"%>
<%@page import="es.rf.tienda.objetos.daos.UsuarioDAOH"%>
<%@ page contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>interno</title>
</head>
<body>
	<div class="container">
	<% 
		String usuario = request.getParameter("user");
		String pass = request.getParameter("password");
		

		
		if(usuario.equals("jordi") && pass.equals("jordi")){
			session.setAttribute("user", usuario);
			response.sendRedirect("inicio.jsp");
		}
		else{
			//response.sendError(response.SC_BAD_REQUEST);
			response.sendRedirect("error.jsp");
		}
	
	
	%>
	</div>
</body>
</html>