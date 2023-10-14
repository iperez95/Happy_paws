<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/css/menu.css">
    <link rel="stylesheet" href="/css/catalogo.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <title>CATALOGO</title>
</head>
<body>

	<div class="titulo">
		<h1>Listado de Protectoras</h1>
		<h1>${mensaje }</h1>
	</div>
<br>
<br> 
<br>

<br>
<table class="table">
	<thead>
	  <tr>
		<th scope="col">Id</th>
		<th scope="col">Nombre</th>
		<th scope="col">Dirección</th>
		<th scope="col">Contacto</th>
		<th scope="col">Acciones</th>
	  </tr>
	</thead>
	<tbody>
		<c:forEach var="prot" items="${protectora}" >
		<tr>		
			<th scope="row">${prot.idprotectora}</th>
			<td>${prot.nombre}</td>
			<td>${prot.direccion}</td>
			<td>${prot.usuario}</td>
			<td><button type="button" class="btn btn-outline-success">Ver</button>
				<button type="button" class="btn btn-outline-primary">Modificar</button>			
				<button type="button" class="btn btn-outline-danger">Borrar</button> </td>
		</tr>
		</c:forEach>
	</tbody>
  </table>
	
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		function limpiarCampos() {
			window.location = '/producto/busqueda';
		}
	</script>
	<script type="text/javascript">
	function ocultarDiv() {
	  var div = document.getElementsByClassName("Elementos");
	  div.style.display = "none"
}
	</script>

</body>

</html>