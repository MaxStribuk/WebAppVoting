package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "artists", schema = "app")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "artists_seq")
    @SequenceGenerator(name = "artists_seq", sequenceName = "artists_id_seq",
            schema = "app", allocationSize = 1)
    private long id;

    @Column(name = "name", nullable = false)
    private String artist;

    public ArtistEntity() {
    }

    public ArtistEntity(long id, String artist) {
        this.id = id;
        this.artist = artist;
    }

    public ArtistEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}