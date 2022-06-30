package uz.buzzard.buzzard_uz.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.buzzard.buzzard_uz.entity.Book;
import uz.buzzard.buzzard_uz.service.services.BookService;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;



    @PostMapping("/booking")
    public HttpEntity<?> bookProduct(@RequestBody Book book){
        return bookService.bookProduct(book);
    }

    @GetMapping("/getByid/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        return bookService.getById(id);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return bookService.getAll();
    }



}
