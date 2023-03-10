package web.controllers;

import dto.request.ArtistDTORequest;
import dto.response.ArtistDTOResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.api.IArtistService;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final IArtistService artistService;

    public ArtistController(IArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArtistDTOResponse> getAll() {
        return artistService.getAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArtistDTOResponse get(@PathVariable("id") Long id) {
        return artistService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody ArtistDTORequest artist) {
        artistService.add(artist);
    }

    @PutMapping(path = "/{id}/version/{version}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("id") Long id,
                       @PathVariable("version") Long version,
                       @RequestBody ArtistDTORequest artist) {
        artistService.update(id, version, artist);
    }

    @DeleteMapping(path = "/{id}/version/{version}")
    public void delete(@PathVariable("id") Long id,
                       @PathVariable("version") Long version) {
        artistService.delete(id, version);
    }
}