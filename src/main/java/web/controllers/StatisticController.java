package web.controllers;

import dto.response.StatisticDTOResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.api.IStatisticsService;

@RestController
@RequestMapping("/results")
public class StatisticController {

    private final IStatisticsService service;

    public StatisticController(IStatisticsService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StatisticDTOResponse get() {
        return service.getStatistics();
    }
}