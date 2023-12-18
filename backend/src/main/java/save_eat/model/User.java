package save_eat.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.util.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

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

    @NotNull
    private LocalDateTime joinAt;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST})
    @Getter(AccessLevel.NONE)
    private List<OAuth> oauthIds = new ArrayList<>();

    @Builder
    private User(
        String name,
        String email,
        @Singular("oauthId") List<OAuth.Credential> oauthIds) {

        setName(name);
        setEmail(email);
        oauthIds.forEach(this::addOAuthId);
        this.joinAt = LocalDateTime.now();
    }

    public List<OAuth> getOAuthIdList() {
        return Collections.unmodifiableList(this.oauthIds);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addOAuthId(OAuth.Credential credential) {
        boolean isUniqueProvider = oauthIds.stream()
            .map(oauth -> oauth.getCredential().getProviderId())
            .noneMatch(credential.getProviderId()::equals);

        Assert.isTrue(isUniqueProvider, "이미 등록된 클라이언트 입니다");

        this.oauthIds.add(new OAuth(this, credential));
    }

}
