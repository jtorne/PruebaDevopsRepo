<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="es.rf.tienda.dominio.Producto" language="java" %>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eliminar Producto</title>
</head>
<body>
	<div class="container">
	<h1>¿Quieres borrar la Categoría?</h1>
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
		if(p.getId_pais()>0){
			String pais= Integer.toString(p.getId_pais());
		}
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
	<form action="Producto?orden=eliminar&id_Prod=<%=p.getId_producto()%>" method="post">
		<label>ID: </label><%=p.getId_producto()%>
		<br>
		<label>Descripción: </label><%=desc %>
		<br>
		<label>Descripción Larga: </label><%=descLarga%>
		<br>
		<label>Precio: </label> <%=precio%> <%=uniVenta%>
		<br>
		<label>Uso Recomendado: </label><%= uso%>
		<br>
		<label>Stock: </label><%= stock %>
		<br>
		<label>Stock Reservado: </label><%=stockRes%>
		<br>
		<label>Stock Alto: </label><%=stockAlto %>
		<br>
		<label>Stock Bajo: </label><%= stockBajo%>
		<br>
		<input id="submit" type="submit" value="Eliminar" class="btn btn-primary">
	</form>
	</div>
</body>
</html>
