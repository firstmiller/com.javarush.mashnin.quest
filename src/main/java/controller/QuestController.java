package controller;

import repository.QuestionRepository;
import service.QuestionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestController extends HttpServlet {
    private final QuestionRepository questionRepository = new QuestionRepository();
    private final QuestionService questionService = new QuestionService(questionRepository);

    @Override
    public void init() {
        getServletContext().setAttribute("questionService", questionService);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("nickname") == null) {
            resp.sendRedirect("/");
            return;
        }
        if (session.getAttribute("currentQuestion") == null) {
            session.setAttribute("currentQuestion", 1L);
            session.setAttribute("counter", 0);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("quest.jsp");
        req.setAttribute("questionService", questionService);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if (req.getParameter("answer") != null) {
            long questionID = (long) session.getAttribute("currentQuestion");
            long answerID = Long.parseLong(req.getParameter("answer"));
            long nextQuestionID = questionService.getAnswerToQuestion(questionID, answerID).orElse(1L);
            session.setAttribute("currentQuestion", nextQuestionID);
        }

        if (req.getParameter("isNewAttempt") != null) {
            session.setAttribute("currentQuestion", 1L);
            int lastCounter = (int) session.getAttribute("counter");
            session.setAttribute("counter", ++lastCounter);
        }

        if (req.getParameter("nickname") != null) {
            String nickname = req.getParameter("nickname");
            session.setAttribute("nickname", nickname);
        }
        resp.sendRedirect(req.getContextPath() + "/quest");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
    }
}
