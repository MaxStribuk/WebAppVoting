package web;

import dto.SavedVoteDTO;
import dto.VoteDTO;
import service.api.IVoteService;
import service.factories.VoteServiceSingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    private final IVoteService service;

    public VoteServlet() {
        this.service = VoteServiceSingleton.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        int artistId = getArtistId(req);
        List<Integer> genreIds = getGenreIds(req);
        String about = getAbout(req);

        VoteDTO vote = new VoteDTO(artistId, genreIds, about);
        service.validate(vote);
        service.save(new SavedVoteDTO(vote));

        PrintWriter writer = resp.getWriter();
        writer.append("Thank you for your response!");
    }

    private int getArtistId(HttpServletRequest req) throws IllegalArgumentException {
        String[] artistIds = req.getParameterValues("artist");
        if (artistIds == null) {
            throw new IllegalArgumentException("User failed to provide artist id ");
        }
        if (artistIds.length > 1) {
            throw new IllegalArgumentException("User provided more than one artist id ");
        }

        try {
            return Integer.parseInt(artistIds[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User failed to provide artist id ");
        }
    }

    private List<Integer> getGenreIds(HttpServletRequest req)
            throws IllegalArgumentException {

        String[] genreIds = req.getParameterValues("genre");
        if (genreIds == null) {
            throw new IllegalArgumentException("User failed to provide genre ids ");
        }

        try {
            return Arrays.stream(genreIds)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User failed to provide genre id ");
        }
    }

    private String getAbout(HttpServletRequest req) throws IllegalArgumentException {
        String[] abouts = req.getParameterValues("about");
        if (abouts == null) {
            throw new IllegalArgumentException("User failed to provide message ");
        }
        if (abouts.length > 1) {
            throw new IllegalArgumentException("User provided more than one message ");
        }
        return abouts[0];
    }
}