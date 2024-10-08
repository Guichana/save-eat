package save_eat.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import save_eat.model.OAuth.OAuthCredential;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    private String email;
    private String imageUrl;

    @NotNull
    private LocalDateTime joinAt;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @Getter(AccessLevel.NONE)
    private List<OAuth> oauthIds = new ArrayList<>();

    @Builder
    private User(
        String name,
        String email,
        String imageUrl,
        @Singular("oauthId") List<OAuthCredential> oauthIds) {

        setName(name);
        setEmail(email);
        setImageUrl(imageUrl);
        oauthIds.forEach(this::addOAuthId);
        this.joinAt = LocalDateTime.now();
    }

    public List<OAuthCredential> getOAuthIdList() {
        return this.oauthIds.stream()
            .map(item -> item.getCredential())
            .toList();
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

    public void addOAuthId(OAuthCredential oauthCredential) {
        boolean isUniqueProvider = oauthIds.stream()
            .map(oauth -> oauth.getCredential().getProviderId())
            .noneMatch(oauthCredential.getProviderId()::equals);

        Assert.isTrue(isUniqueProvider, "이미 등록된 클라이언트 입니다");

        this.oauthIds.add(new OAuth(this, oauthCredential));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            if (((User)obj).id == this.id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(User.class, id);
    }
}
