<%@ page isELIgnored="false" %>
<%@ page import="service.QuestionService" %>
<%@ page import="entity.Question" %>
<%@ page import="entity.Answer" %>
<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    long currentQuestionID = (long) session.getAttribute("currentQuestion");
    QuestionService questionService = (QuestionService) config.getServletContext().getAttribute("questionService");
    Question question = questionService.getQuestionByID(currentQuestionID);
    List<Answer> answerList = question.getAnswers();
    request.setAttribute("question", question);
    request.setAttribute("answerList", answerList);
%>
<html>
<head>
    <title>Квест</title>
    <style>
        <%@include file="/WEB-INF/style/style.css" %>
    </style>
</head>
<body>
<%@include file="/WEB-INF/template/modal.jsp" %>
<%@include file="/WEB-INF/template/header.jsp" %>
<div class="container">
    <div class="content">
        <%if (answerList.size() > 0) { %>
        <h2>${question.text}</h2>
        <h3>Варианты ответов:</h3>
        <form class="answers-form" method="post" action="${pageContext.request.contextPath}/quest">
            <div class="options">
                <c:forEach var="answer" items="${answerList}">
                    <div class="option">
                        <input id="${answer.id}" type="radio" name="answer" value="${answer.id}">
                        <label for="${answer.id}">${answer.text}</label>
                    </div>
                </c:forEach>
            </div>
            <input class="button__answer" type="submit" value="Ответить">
        </form>
        <%} else {%>
        <h3>${question.text}</h3>
        <form method="post" action="${pageContext.request.contextPath}/quest">
            <input type="hidden" name="isNewAttempt" value="true" />
            <button class="button__attempt">Начать новую попытку</button>
        </form>
        <% }%>
    </div>
</div>
<script defer>
    <%@include file="/WEB-INF/script/script.js" %>
</script>
</body>
</html>
