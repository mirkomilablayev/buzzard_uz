package uz.buzzard.buzzard_uz.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book,Long> {
    Optional<Book> findByIdAndIsActive(Long id, Boolean isActive);
    List<Book> findAllByIsActive(Boolean isActive);
}
