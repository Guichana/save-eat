package save_eat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(name = "UNIQUE_OAUTH_ACCOUNT", columnNames = {"provider", "uid"}),
        @UniqueConstraint(name = "ONE_PROVIDER_PER_USER", columnNames = {"user_id", "provider"}),
    }
)
@Getter
@NoArgsConstructor
public class OAuth {

    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String provider;

    @NotNull
    private String uid;


    @Builder
    public OAuth(User user, String provider, String uid) {
        this.user = user;
        this.provider = provider;
        this.uid = uid;
    }

}
