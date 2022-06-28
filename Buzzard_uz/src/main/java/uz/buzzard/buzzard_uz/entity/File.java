package uz.buzzard.buzzard_uz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;

@Data
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
}
