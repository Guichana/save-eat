package save_eat.security;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OAuthUserPrincipal {

    static public class Oidc extends DefaultOidcUser implements UserPrincipal {

        private Integer userId;

        public Oidc(OidcUser oidcUser, Integer userId) {
            super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
            this.userId = userId;
        }

        public Integer getUserId() {
            return userId;
        }

    }

}
