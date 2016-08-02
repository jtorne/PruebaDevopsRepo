<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<title>Nuevo Producto</title>
</head>
<body>
	<div class="container">
		<h1>Nuevo Producto</h1>
		<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
		<hr>
		<form action="Producto?orden=crear" method="post">
			<label>Identificador*: </label><input id="idProd" type="text" name="idProd"><br>
			<label>Descripción*: </label><input id="descripcionProd" type="text" name="descripcionProd"><br>
			<label>Descripción Larga: </label><input type="text" name="descripcionLargaProd"><br>
			<label>Precio*: </label><input id="precioProd" type="text" name="precioProd"><br>
			<label>Stock: </label><input type="text" name="stockProd"><br>
			<label>Unidad Venta*: </label><input id="uniVenProd" type="text" name="uniVenProd"><br>
			<label>País*: </label><input id="paisProd" type="text" name="paisProd"><br>
			<label>ID Categoría*: </label><input id="idCatProd" type="text" name="idCatProd"><br>
			<label>Uso Recomendado: </label><input type="text" name="usoRecProd"><br>
			<label>Stock Reservado: </label><input type="text" name="stkResProd"><br>
			<label>Stock Alto: </label><input type="text" name="stkAltProd"><br>
			<label>Stock Bajo: </label><input type="text" name="stkBajoProd"><br>
			<label>Estado: </label><input type="text" name="estadoProd"><br>
			<input id="submit" type="submit" value="Crear" class="btn btn-primary">
		</form>
	<% 
		if(session.getAttribute("ErrorCreateProd")!=null){
			String message= session.getAttribute("ErrorCreateProd").toString();
			session.removeAttribute("ErrorCreateProd");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
					  message+"</div>");
		}
	%>
	</div>
</body>
</html>
