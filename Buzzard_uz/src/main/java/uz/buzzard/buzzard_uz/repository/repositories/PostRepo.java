package uz.buzzard.buzzard_uz.repository.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.Post;

public interface PostRepo extends JpaRepository<Post,Long> {
}
