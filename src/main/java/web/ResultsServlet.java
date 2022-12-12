package web;

import service.api.IStatisticsService;
import service.factories.StatisticsServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResultsServlet", urlPatterns = "/results")
public class ResultsServlet extends HttpServlet {

    private final IStatisticsService service;

    public ResultsServlet() {
        this.service = StatisticsServiceSingleton.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String results = service.getStringValue();
        PrintWriter writer = resp.getWriter();

        writer.write(results);
    }
}
