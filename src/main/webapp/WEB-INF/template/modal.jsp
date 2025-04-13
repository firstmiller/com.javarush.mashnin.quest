<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<div class="modal_close" id="modal">
    <div class="modal__wrapper">
        <div class="modal__content">
            <h2>Статистика</h2>
            <%if (session.getAttribute("nickname") != null) {%>
            <div class="stat__block">
                <h3>Ваш никнейм: </h3>
                <span><%=session.getAttribute("nickname")%></span>
            </div>
            <div class="stat__block">
                <h3>Количество попыток: </h3>
                <span><%=session.getAttribute("counter")%></span>
            </div>
            <form method="post" action="${pageContext.request.contextPath}/quest">
                <input type="hidden" name="clearSession" value="true"/>
                <button type="submit" class="button__attempt">Сбросить сессию</button>
            </form>
            <%} else {%>
            <h3>Для появления статистики начните игру</h3>
            <%}%>
        </div>
        <div class="modal__close">x</div>
    </div>
</div>