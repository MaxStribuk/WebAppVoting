package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "votes", schema = "app")
public class VoteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "votes_seq")
    @SequenceGenerator(name = "votes_seq", sequenceName = "votes_id_seq",
            schema = "app", allocationSize = 1)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "votes_genres", schema = "app",
            joinColumns = @JoinColumn(name = "vote_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<GenreEntity> genres;

    @Column(name = "about", nullable = false)
    private String about;

    @Column(name = "creation_time", nullable = false)
    private LocalDateTime creationTime;

    @Column(name = "email", nullable = false)
    private String email;

    public VoteEntity() {
    }

    public VoteEntity(ArtistEntity artist, List<GenreEntity> genres,
                      String about, LocalDateTime creationTime, String email) {
        this.artist = artist;
        this.genres = genres;
        this.about = about;
        this.creationTime = creationTime;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public List<GenreEntity> getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}