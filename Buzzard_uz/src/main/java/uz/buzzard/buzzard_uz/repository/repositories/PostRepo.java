package uz.buzzard.buzzard_uz.repository.repositories;


import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.Post;

import java.util.Optional;

public interface PostRepo extends JpaRepository<Post,Long> {
    Optional<Post> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
