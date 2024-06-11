package save_eat.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @OneToMany(mappedBy = "eat")
    private List<Tag> tags;

    @OneToMany(mappedBy = "eat")
    private List<Photo> photos;

    @Entity(name = "eat_photo")
    static class Photo {
        @Id
        @GeneratedValue
        private Integer id;

        @ManyToOne
        private Eat eat;

        @NotNull
        private String url;
    }

    @Entity(name = "eat_tag")
    static class Tag {
        @Id
        @ManyToOne
        private Eat eat;

        @Id
        private String tag_value;
    }

}
