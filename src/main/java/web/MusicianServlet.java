package web;

import service.api.IMusicianService;
import service.factories.MusicianServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MusicianServlet", urlPatterns = "/artists")
public class MusicianServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html; charset=UTF-8");
        IMusicianService service = MusicianServiceSingleton.getInstance();
        PrintWriter writer = resp.getWriter();

        service.getAll().forEach(musician -> writer
                .append(musician.getMusician()).append("<br>"));

    }

}
