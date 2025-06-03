package com.example.demo.controller;

import com.example.demo.entities.Book;
import com.example.demo.entities.dto.BookDto;
import com.example.demo.entities.Book;
import com.example.demo.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    private Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

            @PostMapping("/create")
            public ResponseEntity<Void> createBook(@RequestBody @Valid BookDto info){
                try {
                    bookService.createBook(info);
                    return ResponseEntity.ok().build();
                } catch (Exception e){
                    return ResponseEntity.badRequest().build();
                }
            }

    //Por autor (GET /books?author=Stephen King)
    @GetMapping("/books")
    public ResponseEntity<BookDto> getBookByAuthor(@RequestParam String author) {
        try {
            BookDto book = bookService.getBookByAuthor(author);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    
    }

    //Por ISBN (GET /books/{isbn})
    @GetMapping("/books/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        if (!UUID_REGEX.matcher(isbn).matches()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Book book = bookService.getBook(isbn);
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Todos los libros (GET /books)
    @GetMapping("/books/all")  
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Por rango de paginas (GET /books/range?min=100&max=500)	
    @GetMapping("/books/range")
    public ResponseEntity<List<Book>> getBooksByPageRange(@RequestParam int min, @RequestParam int max) {
        try {
            List<Book> books = bookService.getBooksByPageRange(min, max);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //Por genero (GET /books/genre?genre=Fiction)
    @GetMapping("/books/genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre) {
        try {
            List<Book> books = bookService.getBooksByGenre(genre);
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
