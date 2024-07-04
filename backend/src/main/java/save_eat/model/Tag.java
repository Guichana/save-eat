package save_eat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity(name = "eat_tag")
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UNIQUE_TAG_IN_USER", columnNames = {"user_id", "tag_value"})
})
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private User user;

    private String tag_value;

    public Tag(User user, String value) {
        this.user = user;
        this.tag_value = value;
    }

    String getValue() {
        return this.tag_value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Tag) {
            if (((Tag)obj).id == this.id) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(Tag.class, id);
    }
}
