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

    private static final ApplicationContext applicationContext;
    private static final IArtistService artistService;
    private static final IGenreService genreService;
    private static final IVoteService voteService;
    private static final IStatisticsService statisticsService;
    private static final ISendingService sendingService;

    static {
        applicationContext = new ClassPathXmlApplicationContext("main-contex.xml");
        artistService = applicationContext.getBean("artistService", ArtistService.class);
        genreService = applicationContext.getBean("genreService", GenreService.class);
        voteService = applicationContext.getBean("voteService", VoteService.class);
        statisticsService = applicationContext.getBean("statisticsService", StatisticsService.class);
        sendingService = applicationContext.getBean("emailSendingService", EmailSendingService.class);
    }

    public static void main(String[] args) {

    }
}