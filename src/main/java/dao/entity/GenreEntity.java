package dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "genres", schema = "app")
public class GenreEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "genres_seq")
    @SequenceGenerator(name = "genres_seq",sequenceName = "genres_id_seq",
            schema = "app", allocationSize = 1)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Version
    @Column(name = "version", nullable = false)
    private long version;

    public GenreEntity() {
    }

    public GenreEntity(String title) {
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

    public long getVersion() {
        return version;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreEntity that = (GenreEntity) o;
        return id == that.id && version == that.version && title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, version);
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", version=" + version +
                '}';
    }
}