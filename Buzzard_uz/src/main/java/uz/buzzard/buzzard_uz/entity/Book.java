package uz.buzzard.buzzard_uz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.buzzard.buzzard_uz.entity.baseEntities.BaseEntityId;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends BaseEntityId {
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String phoneNumber;
    private Boolean isActive = true;
    private LocalDateTime bookedAt = LocalDateTime.now();
}
