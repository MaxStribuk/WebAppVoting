package web.servlets;

import dto.StatisticsDTO;
import service.api.IStatisticsService;
import service.factories.StatisticsServiceSingleton;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ResultServlet", urlPatterns = "/results")
public class ResultServlet extends HttpServlet {

    private final IStatisticsService service;

    public ResultServlet() {
        this.service = StatisticsServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        StatisticsDTO statistics = service.getStatistics();
        PrintWriter writer = resp.getWriter();

        writeBestArtists(writer, statistics);
        writeBestGenres(writer, statistics);
        writeAbouts(writer, statistics);
    }

    private void writeBestArtists(PrintWriter writer, StatisticsDTO statistics) {
        writer.append("<b>Best artists:</b><br>");
        statistics.getBestArtists()
                .forEach((key, value) -> writer.append(key.getName())
                        .append(" - ")
                        .append(value.toString())
                        .append(" votes<br>"));
    }

    private void writeBestGenres(PrintWriter writer, StatisticsDTO statistics) {
        writer.append("<b>Best genres:</b><br>");
        statistics.getBestGenres()
                .forEach((key, value) -> writer.append(key.getTitle())
                        .append(" - ")
                        .append(value.toString())
                        .append(" votes<br>"));
    }

    private void writeAbouts(PrintWriter writer, StatisticsDTO statistics) {
        writer.append("<b>Information about voters:</b><br>");
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy");
        statistics.getAbouts()
                .forEach((key, value) -> writer.append(key.format(formatter))
                        .append(" - ")
                        .append(value)
                        .append("<br>"));
    }
}