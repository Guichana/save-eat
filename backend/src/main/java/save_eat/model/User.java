package save_eat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue()
    private Integer id;

    @NotNull
    private String name;

    private String email;

    private String imageUrl;

    @NotNull
    private LocalDateTime joinAt;

    @Builder
    private User(String name, String email, String imageUrl) {
        setName(name);
        setEmail(email);
        setImageUrl(imageUrl);
        this.joinAt = LocalDateTime.now();
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
