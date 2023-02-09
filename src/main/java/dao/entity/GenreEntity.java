package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "genres", schema = "app")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "genres_seq")
    @SequenceGenerator(name = "genres_seq",sequenceName = "genres_id_seq",
            schema = "app", allocationSize = 1)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    public GenreEntity() {
    }

    public GenreEntity(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public GenreEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}