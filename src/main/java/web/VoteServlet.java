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

        String artistId = req.getParameter("artist");
        if (artistId == null || artistId.isBlank()) {
            throw new IllegalArgumentException("User failed to provide artist id");
        }

        String[] genreIds = req.getParameterValues("genre");
        if (genreIds == null) {
            throw new IllegalArgumentException("User failed to provide genre id");
        }
        for (String genreId : genreIds) {
            if (genreId.isBlank()) {
                throw new IllegalArgumentException("User failed to provide genre id");
            }
        }

        String message = req.getParameter("message");
        if (message == null || message.isBlank()) {
            throw new IllegalArgumentException("User failed to provide a message");
        }

        IVoteService service = VoteServiceSingleton.getInstance();

        int musicianId = Integer.parseInt(artistId);
        List<Integer> genres = Arrays.stream(genreIds).map(Integer::parseInt).collect(Collectors.toList());

        VoteDTO vote = new VoteDTO(musicianId, genres, message);

        service.validate(vote);
        service.save(new SavedVoteDTO(vote));

        PrintWriter writer = resp.getWriter();
        writer.append("Thank you for your response!");
    }

}
