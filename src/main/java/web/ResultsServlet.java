package web;

import service.api.IStatisticsService;
import service.factories.StatisticsServiceMemorySingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResultsServlet", urlPatterns = "/results")
public class ResultsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        IStatisticsService service = StatisticsServiceMemorySingleton.getInstance();

        String results = service.getStringValue();
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(results);
        }
    }
}
