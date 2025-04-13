package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

class QuestControllerTest {
    private QuestController questController;
    private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    private RequestDispatcher requestDispatcher;
    private HttpSession httpSession;

    @BeforeEach
    void init() {
        questController = new QuestController();
        httpRequest = Mockito.mock(HttpServletRequest.class);
        httpResponse = Mockito.mock(HttpServletResponse.class);
        httpSession = Mockito.mock(HttpSession.class);
        requestDispatcher = Mockito.mock(RequestDispatcher.class);

        Mockito.when(httpRequest.getSession()).thenReturn(httpSession);
        Mockito.when(httpRequest.getRequestDispatcher("quest.jsp")).thenReturn(requestDispatcher);
    }

    @Test
    void redirectIfNoNickname() throws IOException, ServletException {
        Mockito.when(httpSession.getAttribute("nickname")).thenReturn(null);
        questController.doGet(httpRequest, httpResponse);
        Mockito.verify(httpResponse).sendRedirect(httpRequest.getContextPath() + "/");
    }
    @Test
    void defaultValuesSessionIfNoCurrentQuestion() throws ServletException, IOException {
        Mockito.when(httpSession.getAttribute("nickname")).thenReturn("name");
        Mockito.when(httpSession.getAttribute("currentQuestion")).thenReturn(null);
        questController.doGet(httpRequest, httpResponse);
        Mockito.verify(httpSession).setAttribute("currentQuestion", 1L);
        Mockito.verify(httpSession).setAttribute("counter", 0);
    }

    @Test
    void postAnswerParameter() throws IOException {
        Mockito.when(httpRequest.getParameter("answer")).thenReturn("1");
        Mockito.when(httpSession.getAttribute("currentQuestion")).thenReturn(1L);
        Mockito.when(httpRequest.getParameter("answer")).thenReturn("1");
        questController.doPost(httpRequest, httpResponse);
        Mockito.verify(httpSession).setAttribute(Mockito.eq("currentQuestion"), Mockito.anyLong());
    }
    @Test
    void postClearSession() throws IOException {
        Mockito.when(httpRequest.getParameter("clearSession")).thenReturn("true");
        questController.doPost(httpRequest, httpResponse);
        Mockito.verify(httpSession).invalidate();
    }
    @Test
    void postNewAttempt() throws IOException {
        Mockito.when(httpRequest.getParameter("isNewAttempt")).thenReturn("true");
        Mockito.when(httpSession.getAttribute("counter")).thenReturn(0);
        questController.doPost(httpRequest, httpResponse);
        Mockito.verify(httpResponse).sendRedirect(httpRequest.getContextPath() + "/quest");
    }
}