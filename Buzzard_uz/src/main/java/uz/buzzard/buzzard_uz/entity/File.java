package uz.buzzard.buzzard_uz.entity;


import lombok.*;
import org.hibernate.Hibernate;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File extends BaseEntityId {
    private String contentType;
    private Long contentSize;
    private String originalName;
    private String generatedName;
    private String filePath;
    private Boolean isDeleted = false;
    private String extension;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        File file = (File) o;
        return getId() != null && Objects.equals(getId(), file.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
