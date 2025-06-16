package com.alura.literatura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    private String language;
    private Long downloadCount; // <--- DEBE SER Long

    // Constructor vacío (NECESARIO PARA JPA)
    public Book() {}

    // ESTE ES EL CONSTRUCTOR QUE GutendexApiService DEBE LLAMAR
    // Asegúrate de que los tipos de los parámetros coincidan y que los valores se asignen
    public Book(String title, Author author, String language, Long downloadCount) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.downloadCount = downloadCount;
    }

    // Asegúrate de que NO esté este constructor (que no asigna valores):
    // public Book(String bookTitle, String language, Long downloadCount, Author author) { }

    // Getters y Setters (asegúrate de que downloadCount sea Long aquí también)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public Long getDownloadCount() { return downloadCount; } // <--- Long
    public void setDownloadCount(Long downloadCount) { this.downloadCount = downloadCount; } // <--- Long

    @Override
    public String toString() {
        return "---------- Libro ----------" +
                "\nTítulo: " + title +
                "\nAutor: " + (author != null ? author.getName() : "Desconocido") +
                "\nIdioma: " + language +
                "\nNúmero de descargas: " + downloadCount +
                "\n---------------------------";
    }
}