package web.servlets;

import dto.GenreDTO;
import service.api.IGenreService;
import service.factories.GenreServiceSingleton;
import web.util.RequestParamHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GenreServlet", urlPatterns = "/genres")
public class GenreServlet extends HttpServlet {

    private final IGenreService service;

    public GenreServlet() {
        this.service = GenreServiceSingleton.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        service.getAll()
                .forEach(genre -> writer.append(String.valueOf(genre.getId()))
                        .append(" - ")
                        .append(genre.getTitle())
                        .append("<br>"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String genre = RequestParamHandler.getRequestParam(req,
                RequestParamHandler.GENRE_PARAM_NAME);
        service.add(new GenreDTO(genre));

        PrintWriter writer = resp.getWriter();
        writer.append(genre)
                .append(" added successfully");
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String id =RequestParamHandler.getRequestParam(req,
                RequestParamHandler.ID_PARAM_NAME);
        long genreId = RequestParamHandler.getID(id);
        String genre = RequestParamHandler.getRequestParam(req,
                RequestParamHandler.GENRE_PARAM_NAME);
        service.update(new GenreDTO(genreId, genre));

        PrintWriter writer = resp.getWriter();
        writer.append(genre)
                .append(" update successfully");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String id =RequestParamHandler.getRequestParam(req,
                RequestParamHandler.ID_PARAM_NAME);
        long genreId = RequestParamHandler.getID(id);
        service.delete(genreId);

        PrintWriter writer = resp.getWriter();
        writer.append("Genre delete successfully");
    }
}