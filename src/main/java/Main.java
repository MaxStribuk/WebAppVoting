import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.ArtistService;
import service.EmailSendingService;
import service.GenreService;
import service.StatisticsService;
import service.VoteService;
import service.api.IArtistService;
import service.api.IGenreService;
import service.api.ISendingService;
import service.api.IStatisticsService;
import service.api.IVoteService;

public class Main {

    private static final ApplicationContext applicationContext =
            new ClassPathXmlApplicationContext("main-contex.xml");

    public static void main(String[] args) {
        IArtistService artistService = applicationContext.getBean("artistService",
                ArtistService.class);
        IGenreService genreService = applicationContext.getBean("genreService",
                GenreService.class);
        IVoteService voteService = applicationContext.getBean("voteService",
                VoteService.class);
        IStatisticsService statisticsService = applicationContext.getBean("statisticsService",
                StatisticsService.class);
        ISendingService sendingService = applicationContext.getBean("emailSendingService",
                EmailSendingService.class);


    }
}