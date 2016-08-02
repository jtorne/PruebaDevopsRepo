<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="es.rf.tienda.dominio.Producto" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Producto</title>
</head>
<body>
<div class="container">
	<h1>Producto Seleccionado</h1>
	<a href="index.jsp"><h2><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Inicio</h2></a>
	<hr>
	<%
		Producto p= (Producto)session.getAttribute("Producto");
		String id= p.getId_producto();
		String desc=p.getPro_descripcion();
		String descLarga="";
		if(p.getPro_desLarga()!=null){
			desc=p.getPro_desLarga();
		}
		String precio= Double.toString(p.getPro_precio());
		String stock="";
		if(p.getPro_stock()>0){
			stock= Integer.toString(p.getPro_stock());
		}
		String uniVenta = p.getPro_uniVenta();
		String pais= Integer.toString(p.getId_pais());
		String uso="";
		if(p.getPro_usoRecomendado()!=null){
			uso =p.getPro_usoRecomendado();
		}
		String idCat= Integer.toString(p.getId_categoria());
		String stockRes="";
		if(p.getPro_stkReservado()>0){
			stockRes=Integer.toString(p.getPro_stkReservado());
		}
		String stockAlto="";
		if(p.getPro_nStkAlto()>0){
			stockAlto=Integer.toString(p.getPro_nStkAlto());
		}
		String stockBajo="";
		if(p.getPro_nStkBajo()>0){
			stockBajo=Integer.toString(p.getPro_nStkBajo());
		}
	%>
	<form action="Producto?orden=update&id_Prod=<%=id%>" method="post">
		<label>ID: </label> <%=id%>
		<br>
		<label>Descripción: </label><input type="text" value="<%=desc %>" name="descripcionProd">
		<br>
		<label>Descripción Larga: </label><input type="text" value="<%=descLarga%>" name="descripcionLargaProd">
		<br>
		<label>Precio: </label> <input type="text" value="<%=precio%> " name="precioProd">
		<br>
		<label>Unidad de Venta </label><input type="text" value="<%=uniVenta%>" name="uniVenProd">
		<br>
		<label>Uso Recomendado: </label><input type="text" value="<%= uso%>" name="usoRecProd">
		<br>
		<br>
		<label>Pais: </label><input type="text" value="<%= pais%>" name="paisProd">
		<br>
		<br>
		<label>ID Categoría: </label><input type="text" value="<%= pais%>" name="idCatProd">
		<br>
		<label>Stock: </label><input type="text" value="<%= stock %>" name="stockProd">
		<br>
		<label>Stock Reservado: </label><input type="text" value="<%=stockRes%>" name="stkResProd">
		<br>
		<label>Stock Alto: </label><input type="text" value="<%=stockAlto %>" name="stkAltProd">
		<br>
		<label>Stock Bajo: </label><input type="text" value="<%= stockBajo%>" name="stkBajoProd">
		<br>
		<input type="submit" value="Modificar" class="btn btn-primary">
	</form>
	<% 
		if(session.getAttribute("ErrorUpdateCat")!=null){
			String message= session.getAttribute("ErrorUpdateCat").toString();
			session.removeAttribute("ErrorUpdateCat");
			out.println("<div class=\"alert alert-danger\" role=\"alert\">"+
					  message+"</div>");
		}
		%>
	</div>
</body>
</html>