package uz.buzzard.buzzard_uz.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.buzzard.buzzard_uz.entity.Book;

public interface BookRepo extends JpaRepository<Book,Long> {

}
