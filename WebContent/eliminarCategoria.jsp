<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="es.rf.tienda.dominio.Categoria" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Categoria</title>
</head>
<body>

	<div class="container">
	<h1>¿Quieres borrar la Categoría?</h1>
	<%
		Categoria c= (Categoria)session.getAttribute("Categoria");
		out.println("<label> Identificador : "+c.getId_categoria()+"</label>");
		String desc="";
		if(c.getCat_descripcion()!=null){
			desc=c.getCat_descripcion();
		}	
	%>
	<form action="Categoria?orden=eliminar&id_cat=<%=c.getId_categoria()%>" method="post">
		<label>Nombre: <%=c.getCat_nombre()%></label>
		<br>
		<label>Descripción: <%=desc%></label>
		<br>
		<input id="submit" type="submit" value="Eliminar" class="btn btn-primary">
	</form>
	</div>
</body>
</html>
