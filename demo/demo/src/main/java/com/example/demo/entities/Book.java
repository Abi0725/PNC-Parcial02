package com.example.demo.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Set;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    @Pattern(regexp = "^(?!\\d+$).*$", message = "El título no puede contener solo números")
    private String title;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;
    @Column(name = "publicationyear", nullable = false )
    @Min(value = 1900, message = "Año de publicación debe ser mayor o igual a 1900")
    @Max(value = 2025, message = "Año de publicación debe ser menor o igual a 2025")
    private int publicationYear;
    @Column(name = "language", nullable = true)
    private String language;
    @Column(name = "pages", nullable = false)
    @Min(value = 10, message = "El número de páginas debe ser al menos 10")
    private int pages;
    @Column(name = "genre", nullable = false)	
    private String genre;
    

}


