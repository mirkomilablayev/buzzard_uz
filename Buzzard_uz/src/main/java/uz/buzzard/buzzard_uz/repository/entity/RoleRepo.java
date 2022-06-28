package uz.buzzard.buzzard_uz.repository.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.UserRole;
import uz.buzzard.buzzard_uz.repository.BaseRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<UserRole,Long>, BaseRepository {
Optional<UserRole> findByNameAndIsActive(String name, Boolean isActive);
}
