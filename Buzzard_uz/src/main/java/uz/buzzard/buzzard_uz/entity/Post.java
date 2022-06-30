package uz.buzzard.buzzard_uz.entity;


import lombok.*;
import org.hibernate.Hibernate;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseEntityId {
    private String title;
    private String text;
    @ManyToOne
    private File file;
    private Boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Post post = (Post) o;
        return getId() != null && Objects.equals(getId(), post.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
