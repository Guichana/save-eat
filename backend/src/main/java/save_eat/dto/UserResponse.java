package save_eat.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import save_eat.model.User;

@Builder
public class UserResponse implements Serializable {

    public String name;
    public String email;
    public String imageUrl;
    public LocalDateTime joinAt;

    static public UserResponse from(User user) {
        return builder()
            .email(user.getEmail())
            .name(user.getName())
            .joinAt(user.getJoinAt())
            .imageUrl(user.getImageUrl())
            .build();
    }

}
