package save_eat.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UNIQUE_OAUTH_ACCOUNT", columnNames = {"providerId", "uid"}),
    @UniqueConstraint(name = "ONE_PROVIDER_PER_USER", columnNames = {"user_id", "providerId"}),
})
@Getter
@NoArgsConstructor
public class OAuth {

    @Id
    @GeneratedValue()
    private Integer id;

    @ManyToOne
    @NotNull
    private User user;

    @Embedded
    private OAuthCredential credential;

    public OAuth(User user, OAuthCredential credential) {
        this.user = user;
        this.credential = credential;
    }

    @Embeddable
    @Getter
    @NoArgsConstructor
    static public class OAuthCredential implements Serializable {

        @NotNull
        private String providerId;

        @NotNull
        private String uid;

        public OAuthCredential(String providerId, String uid) {
            this.providerId = providerId;
            this.uid = uid;
        }

    }

}
