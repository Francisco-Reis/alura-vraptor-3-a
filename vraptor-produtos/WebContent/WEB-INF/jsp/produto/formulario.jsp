<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de Produto</title>
</head>
<body>

<form action="<c:url value="/produto/adiciona"/>" method="post">
<label>Nome <input type="text" name="produto.nome"></label><br>
<label>Descrição <input type="text" name="produto.descricao"></label><br>
<label>Preço <input type="text" name="produto.preco"></label><br>
<label>Cor <input type="text" name="produto.cor"></label><br>
<input type="submit" value="Enviar">
</form>

</body>
</html>