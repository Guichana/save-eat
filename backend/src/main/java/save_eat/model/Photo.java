package save_eat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "eat_photo")
@NoArgsConstructor
public class Photo {
    @Id
    @GeneratedValue
    @Getter
    private Integer id;

    @ManyToOne
    @Setter(value = AccessLevel.PROTECTED)
    private Eat eat;

    @NotNull
    @Getter
    private String fileId;

    public Photo(String fileId) {
        this.fileId = fileId;
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
