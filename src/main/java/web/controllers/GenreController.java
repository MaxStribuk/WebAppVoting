package web.controllers;

import dto.request.GenreDTORequest;
import dto.response.GenreDTOResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.api.IGenreService;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final IGenreService genreService;

    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GenreDTOResponse> getAll() {
        return genreService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GenreDTOResponse get(@PathVariable("id") Long id) {
        return genreService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody GenreDTORequest genre) {
        genreService.add(genre);
    }

    @PutMapping(path = "/{id}/version/{version}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long id,
                       @PathVariable("version") Long version,
                       @RequestBody GenreDTORequest genre) {
        genreService.update(id, version, genre);
    }

    @DeleteMapping(path = "/{id}/version/{version}")
    public void delete(@PathVariable("id") Long id,
                       @PathVariable("version") Long version) {
        genreService.delete(id, version);
    }
}