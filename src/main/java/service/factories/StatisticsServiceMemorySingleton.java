package service.factories;

import service.StatisticsService;
import service.api.IStatisticsService;

public class StatisticsServiceMemorySingleton {

    private volatile static IStatisticsService instance = null;

    private StatisticsServiceMemorySingleton() {

    }

    public static IStatisticsService getInstance() {
        if(instance == null){
            synchronized (StatisticsServiceMemorySingleton.class){
                if(instance == null){
                    instance = new StatisticsService(
                            VoteServiceMemorySingleton.getInstance(),
                            GenreServiceMemorySingleton.getInstance(),
                            MusicianServiceMemorySingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
