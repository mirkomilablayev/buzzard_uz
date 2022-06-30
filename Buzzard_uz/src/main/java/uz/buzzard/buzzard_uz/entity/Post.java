package uz.buzzard.buzzard_uz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends BaseEntityId {
    private String title;
    private String text;
    @ManyToOne
    private File file;
    private Boolean isDeleted = false;
}
