package web;

import dto.StatisticsDTO;
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

        StatisticsDTO statistics = service.getStatistics();
        PrintWriter writer = resp.getWriter();

        writeBestArtists(writer, statistics);
        writeBestGenres(writer, statistics);
        writeAbouts(writer, statistics);
    }

    private void writeBestArtists(PrintWriter writer, StatisticsDTO statistics) {
        statistics.getBestArtists()
                .forEach((key, value) -> writer.append(key.getArtist())
                        .append(" - ")
                        .append(value.toString())
                        .append(" votes<br>"));
    }

    private void writeBestGenres(PrintWriter writer, StatisticsDTO statistics) {
        statistics.getBestGenres()
                .forEach((key, value) -> writer.append(key.getGenre())
                        .append(" - ")
                        .append(value.toString())
                        .append(" votes<br>"));
    }

    private void writeAbouts(PrintWriter writer, StatisticsDTO statistics) {
        statistics.getAbouts()
                .forEach((key, value) -> writer.append(key.toString())
                        .append(" - ")
                        .append(value)
                        .append("<br>"));
    }
}