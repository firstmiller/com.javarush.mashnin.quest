<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        <%@include file="/WEB-INF/style/style.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/template/modal.jsp" %>
<%@include file="/WEB-INF/template/header.jsp" %>
<div class="container">
    <div class="content">
        <p>
            Вы — советский шпион, задержанный в США. Вас доставили на допрос как подозреваемого в шпионаже. Цель — не
            выдать
            себя.
        </p>
        <div>
            <div><strong>Имя:</strong> Алексей Морозов</div>
            <div><strong>Дата рождения:</strong> 12 марта 1950</div>
            <div><strong>Место рождения:</strong> Свердловск, СССР</div>
        </div>
        <p>
            Легенда: студент Caltech с 1974 года, прибыл по обмену из МФТИ. Работает ассистентом в лаборатории
            кибербезопасности.
            Настоящая цель — доступ к секретным исследованиям в сфере шифрования и спутников. Задержан возле закрытой
            зоны
            кампуса.
        </p>
        <%if (session.getAttribute("nickname") == null) { %>
        <form class="legend__form" method="post" action="${pageContext.request.contextPath}/quest">
            <div class="login-block">
                <label>Ваш Nickname</label>
                <input type="text" minlength="5" required name="nickname">
            </div>
            <button class="starting-button">Приступить!</button>
        </form>
        <%} else {%>
        <form class="legend__form" method="get" action="${pageContext.request.contextPath}/quest">
            <button class="starting-button">Продолжить!</button>
        </form>
        <%}%>
    </div>
</div>
<script defer>
    <%@include file="/WEB-INF/script/script.js" %>
</script>
</body>
</html>