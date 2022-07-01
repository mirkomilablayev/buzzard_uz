package uz.oshxona.oshxona_uzbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.oshxona.oshxona_uzbot.entity.UserRole;

import java.util.Optional;

public interface UserRoleRepo extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByName(String name);
}
