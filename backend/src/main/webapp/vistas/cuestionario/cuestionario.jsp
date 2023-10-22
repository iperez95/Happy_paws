<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../menu.jsp"></jsp:include>
<h1>Cuestionario</h1>


<form action="/cuestionario/nuevo" method="post">
    <c:forEach var="pre" items="${preguntas}">
        ${pre.pregunta}
        <p><input type="text" name="idpregunta" value="${pre.idpregunta}">idpregunta</p>
        <p><input type="text-area" name="respuesta" >respuesta</p>
    </c:forEach>
    
    <p><input type="submit" value="Enviar"></p>

</form>



</body>
</html>