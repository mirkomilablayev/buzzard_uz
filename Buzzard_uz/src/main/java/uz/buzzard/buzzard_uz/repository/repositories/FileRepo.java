package uz.buzzard.buzzard_uz.repository.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.File;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File,Long> {
    Optional<File> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
