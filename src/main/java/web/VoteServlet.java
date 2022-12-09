package web;

import dto.VoteDTO;
import service.api.IVoteService;
import service.factories.VoteServiceMemorySingleton;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "VoteServlet", urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        IVoteService service = VoteServiceMemorySingleton.getInstance();

        String musician = req.getParameter("artist");
        String[] genres = req.getParameterValues("genre");
        String username = req.getParameter("username");
        String message = req.getParameter("message");

        UserMessage userMessage = new UserMessage(username, message);
        VoteDTO vote = new VoteDTO(musician, genres, userMessage);

        service.validate(vote);
        service.save(vote);

        try (PrintWriter writer = resp.getWriter()) {
            writer.append("Thank you for your response, ")
                    .append(username);
        }
    }


}
