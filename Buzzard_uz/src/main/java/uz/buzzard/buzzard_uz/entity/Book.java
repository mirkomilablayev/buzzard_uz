package uz.buzzard.buzzard_uz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntityId {
    private String fullName;
    private String phoneNumber;
    private LocalDateTime bookedAt;
}
