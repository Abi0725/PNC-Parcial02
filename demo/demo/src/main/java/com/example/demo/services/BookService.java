package com.example.demo.services;

import com.example.demo.entities.dto.BookDto;
import com.example.demo.entities.*;
import com.example.demo.repositories.BookRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@NoArgsConstructor
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void createBook(BookDto bookInfo) {
        Book book = new Book();
        book.setTitle(bookInfo.getTitle());
        book.setAuthor(bookInfo.getAuthor());
        book.setIsbn(bookInfo.getIsbn());
        book.setPublicationYear(bookInfo.getPublicationYear());
        book.setLanguage(bookInfo.getLanguage());
        book.setPages(bookInfo.getPages());
        book.setGenre(bookInfo.getGenre());
        bookRepository.save(book);
    }

    public Book getBook(String isbn){
        Optional<Book> optionalBook = bookRepository.findById(UUID.fromString(isbn));
        if (optionalBook.isEmpty()){
            throw new RuntimeException("Book not found");
        }
        return optionalBook.get();
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public BookDto getBookByAuthor(String author){
        Optional<Book> optionalBook = bookRepository.findBookByAuthor(author);
        if (optionalBook.isEmpty()){
            throw new RuntimeException("User not found");
        }
        Book book = optionalBook.get();
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublicationYear(),
                book.getLanguage(),
                book.getPages(),
                book.getGenre()
        );
    }

    // Por ISBN (GET /books/{isbn})
    public Book getBookByIsbn(String isbn) {
        Optional<Book> optionalBook = bookRepository.findById(UUID.fromString(isbn));
        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        return optionalBook.get();
    }

    //por genero (GET /books/genre/{genre})
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .toList();
    }

    //por rango de paginas (GET /books/range?min=100&max=500)
    public List<Book> getBooksByPageRange(int min, int max) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getPages() >= min && book.getPages() <= max)
                .toList();
    }

}

