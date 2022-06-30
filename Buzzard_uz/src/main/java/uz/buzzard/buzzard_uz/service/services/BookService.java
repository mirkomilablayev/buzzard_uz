package uz.buzzard.buzzard_uz.service.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.buzzard.buzzard_uz.entity.Book;
import uz.buzzard.buzzard_uz.repository.repositories.BookRepo;
import uz.buzzard.buzzard_uz.tools.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public HttpEntity<?> bookProduct(Book book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepo.save(book));
    }

    public HttpEntity<?> getById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepo.findByIdAndIsActive(id,true).orElseThrow(() -> new ResourceNotFoundException("")));
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookRepo.findAllByIsActive(true));
    }
}
