package save_eat.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import lombok.Getter;

@Entity
@NoArgsConstructor
public class Eat {

    @Id
    @GeneratedValue()
    @Getter
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    @NotNull
    private Integer userId;

    @NotNull
    @Getter
    @Setter
    private String placeName;

    @NotNull
    @Getter
    @Setter
    private LocalDate eatDate;

    @NotNull
    @Getter
    @Setter
    private String foodName;

    @NotNull
    @Getter
    @Setter
    private Short rating;

    @NotNull
    @Getter
    @Setter
    private Integer price;

    @NotNull
    @Getter
    @Setter
    private String comment;

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

    @Builder
    Eat(
        Integer userId,
        String placeName,
        LocalDate eatDate,
        String foodName,
        Short rating,
        Integer price,
        String comment) {
        this.userId = userId;
        setPlaceName(placeName);
        setEatDate(eatDate);
        setFoodName(foodName);
        setRating(rating);
        setPrice(price);
        setComment(comment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Eat) {
            if (((Eat)obj).id == this.id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Eat.class, id);
    }

}
