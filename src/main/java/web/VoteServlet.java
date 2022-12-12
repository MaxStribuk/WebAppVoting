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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String[] artistIds = req.getParameterValues("artist");
        if (artistIds == null) {
            throw new IllegalArgumentException("User failed to provide artist id");
        }
        if (artistIds.length > 1) {
            throw new IllegalArgumentException("User provided more than one artist id");
        }

        int musicianId;
        try {
            musicianId = Integer.parseInt(artistIds[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User failed to provide artist id");
        }

        String[] genreIds = req.getParameterValues("genre");
        if (genreIds == null) {
            throw new IllegalArgumentException("User failed to provide genre ids");
        }

        List<Integer> genres;
        try {
            genres = Arrays.stream(genreIds).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("User failed to provide genre id");
        }

        String[] messages = req.getParameterValues("message");
        if (messages==null){
            throw new IllegalArgumentException("User failed to provide message");
        }
        if (messages.length > 1) {
            throw new IllegalArgumentException("User provided more than one message");
        }

        VoteDTO vote = new VoteDTO(musicianId, genres, messages[0]);
        IVoteService service = VoteServiceSingleton.getInstance();
        service.validate(vote);
        service.save(new SavedVoteDTO(vote));

        PrintWriter writer = resp.getWriter();
        writer.append("Thank you for your response!");
    }

}
