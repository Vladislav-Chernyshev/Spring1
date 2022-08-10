package ru.chernyshev;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int param1 = Integer.parseInt(req.getParameter("param1"));
        int param2 = Integer.parseInt(req.getParameter("param2"));

        int sum = param1 + param2;
        resp.getWriter().println("<p>" + sum + "</p>");
    }
}
