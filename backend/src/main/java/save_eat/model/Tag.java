package save_eat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;

@Entity(name = "eat_tag")
@Table(uniqueConstraints = {
    @UniqueConstraint(name = "UNIQUE_TAG_IN_EAT", columnNames = {"eat_id", "tag_value"})
})
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "eat_id")
    private Eat eat;

    @Column(name = "tag_value")
    private String tagValue;

    public Tag(Eat eat, String value) {
        this.eat = eat;
        this.tagValue = value;
    }

    String getValue() {
        return this.tagValue;
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
