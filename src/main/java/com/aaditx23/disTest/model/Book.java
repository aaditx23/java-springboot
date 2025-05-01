package com.aaditx23.disTest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    private String genre;
    private int publishedYear;

}
