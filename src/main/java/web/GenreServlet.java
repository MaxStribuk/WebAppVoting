package web;

import service.api.IGenreService;
import service.factories.GenreServiceMemorySingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        IGenreService service = GenreServiceMemorySingleton.getInstance();

        try(PrintWriter writer = resp.getWriter();) {
            service.getAllGenres().forEach(genre ->
                    writer.append(genre.getGenre()).append("\n"));
        }
    }
}
