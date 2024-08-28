package save_eat.security;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

public enum OAuthProvider {

    google("https://accounts.google.com");

    @Getter
    private String issuer;

    public String getProviderId() {
        return this.name();
    }

    OAuthProvider(String issuer) {
        this.issuer = issuer;
    }

    static Map<String, OAuthProvider> byIssuer = Stream.of(values())
        .collect(Collectors.toMap(OAuthProvider::getIssuer, item -> item));

    public static OAuthProvider fromIssuer(String issuer) {
        return byIssuer.get(issuer);
    }
}
