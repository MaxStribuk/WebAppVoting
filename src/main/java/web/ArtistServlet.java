package web;

import service.api.IArtistService;
import service.factories.ArtistServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MusicianServlet", urlPatterns = "/artists")
public class ArtistServlet extends HttpServlet {

    private final IArtistService service;

    private ArtistServlet() {
        this.service = ArtistServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        service.getAll()
                .forEach(artist -> writer.append(artist.getArtist())
                                           .append("<br>"));
    }
}