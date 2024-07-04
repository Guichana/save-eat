package save_eat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity(name = "eat_photo")
public class Photo {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Eat eat;

    @NotNull
    private String imageUrl;

    public Photo(Eat eat, String imageUrl) {
        this.eat = eat;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Photo) {
            if (((Photo)obj).id == this.id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Photo.class, id);
    }
}
