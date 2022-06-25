package uz.buzzard.buzzard_uz.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long>, BaseRepository {
    Optional<User> findByUsernameAndIsDeleted(String username, Boolean isDeleted);
    Boolean existsByUsernameAndIsDeleted(String username, Boolean isDeleted);
    Optional<User> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
