package uz.buzzard.buzzard_uz.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.User;
import uz.buzzard.buzzard_uz.repository.BaseRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long>, BaseRepository {
    Optional<User> findByUsernameAndIsDeleted(String username, Boolean isDeleted);
    Boolean existsByUsernameAndIsDeleted(String username, Boolean isDeleted);
    Optional<User> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
