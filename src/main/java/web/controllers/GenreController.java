package web.controllers;

import dto.request.GenreDTORequest;
import dto.response.GenreDTOResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.api.IGenreService;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final IGenreService genreService;

    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<GenreDTOResponse> getAll() {
        return genreService.getAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public GenreDTOResponse get(@PathVariable("id") Long id) {
        return genreService.get(id);
    }

    @RequestMapping(method = RequestMethod.POST,
            headers = {"Content-Type=application/json;charset=utf-8"})
    public void add(@RequestBody GenreDTORequest genre) {
        genreService.add(genre);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable("id") Long id,
                       @RequestBody GenreDTORequest genre) {
        genreService.update(id, genre);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        genreService.delete(id);
    }
}