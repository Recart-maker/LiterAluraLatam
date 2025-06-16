package com.alura.literatura.controller;

import com.alura.literatura.service.BookService;
import com.alura.literatura.model.Author;
import com.alura.literatura.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
// Asegúrate de que no haya import java.util.Scanner; aquí

@Component
public class BookController {

    @Autowired
    private BookService bookService;


    public void displayMenu() {
        // Solo imprime el menú, sin bucle ni input del usuario
        System.out.println("\nElija la opcion a traves de su numero:");
        System.out.println("1- buscar libro por titulo");
        System.out.println("2- listar libros registrados");
        System.out.println("3- listar autores registrados");
        System.out.println("4- listar autores vivos en un determinado año");
        System.out.println("5- listar libros por idioma");
        System.out.println("6- eliminar todos los libros y autores"); // ¡NUEVA OPCIÓN!
        System.out.println("0- salir");
    }

    // Ahora recibe el título como parámetro
    public void searchBookByTitle(String title) {
        bookService.searchAndSaveBook(title);
    }

    public void listAllBooks() {
        List<Book> books = bookService.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void listAllAuthors() {
        List<Author> authors = bookService.listAllAuthors();
        if (authors.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    // Ahora recibe el año como parámetro
    public void listAuthorsAliveInYear(Integer year) {
        List<Author> authors = bookService.listAuthorsAliveInYear(year);
        if (authors.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year + ".");
        } else {
            authors.forEach(System.out::println);
        }
    }

    // Ahora recibe el idioma como parámetro
    public void listBooksByLanguage(String language) {
        List<Book> books = bookService.listBooksByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados en el idioma '" + language + "'.");
        } else {
            books.forEach(System.out::println);
        }
    }



        // Nuevo método para eliminar todos los datos
        public void deleteAllBooksAndAuthors() {
            bookService.deleteAllData();
        }
    }
