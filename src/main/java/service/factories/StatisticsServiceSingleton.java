package service.factories;

import service.StatisticsService;
import service.api.IStatisticsService;

public class StatisticsServiceSingleton {

    private volatile static IStatisticsService instance = null;

    private StatisticsServiceSingleton() {

    }

    public static IStatisticsService getInstance() {
        if(instance == null){
            synchronized (StatisticsServiceSingleton.class){
                if(instance == null){
                    instance = new StatisticsService(
                            VoteServiceSingleton.getInstance(),
                            GenreServiceSingleton.getInstance(),
                            MusicianServiceSingleton.getInstance()
                    );
                }
            }
        }
        return instance;
    }
}
