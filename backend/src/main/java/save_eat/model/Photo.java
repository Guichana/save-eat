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
}
