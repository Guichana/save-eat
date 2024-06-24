package save_eat.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.Getter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Eat {

    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String placeName;

    @NotNull
    private LocalDateTime eatDate;

    @NotNull
    private String eatName;

    @NotNull
    private Short rating;

    @NotNull
    private Integer price;

    @NotNull
    private String comment;

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @ManyToMany()
    private List<Tag> tags;

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    public void addTag(Tag tag) {
        if (this.tags.contains(tag) != true) {
            this.tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    @Getter(value = AccessLevel.NONE)
    @Setter(value = AccessLevel.NONE)
    @OneToMany(mappedBy = "eat")
    private List<Photo> photos;

    public List<Photo> getPhotos() {
        return Collections.unmodifiableList(photos);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);
    }

    public void removePhoto(Photo photo) {
        this.photos.remove(photo);
    }

}
