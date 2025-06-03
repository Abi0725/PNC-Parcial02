package com.example.demo.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Data
public class BookDto {

    private UUID id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String language;   
    private int pages;
    private String genre;
}
