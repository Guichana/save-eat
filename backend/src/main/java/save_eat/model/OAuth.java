package save_eat.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Integer userId;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OAuth) {
            if (((OAuth)obj).id == this.id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(OAuth.class, id);
    }

}
