package com.alura.literatura.service;

import com.alura.literatura.model.Author;
import com.alura.literatura.model.Book;
import com.alura.literatura.repository.AuthorRepository;
import com.alura.literatura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {




    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GutendexApiService gutendexApiService;

    @Transactional
    public void searchAndSaveBook(String title) {
        Optional<Book> existingBook = bookRepository.findByTitleIgnoreCase(title);
        if (existingBook.isPresent()) {
            System.out.println("El libro \"" + title + "\" ya está registrado en la base de datos.");
            return;
        }

        Optional<Book> apiBook = gutendexApiService.searchBookByTitle(title);
        if (apiBook.isPresent()) {
            Book bookToSave = apiBook.get();
            Author author = bookToSave.getAuthor();

            if (author != null) {
                Optional<Author> existingAuthor = authorRepository.findByNameIgnoreCase(author.getName());
                if (existingAuthor.isPresent()) {
                    bookToSave.setAuthor(existingAuthor.get());
                    existingAuthor.get().getBooks().add(bookToSave);
                } else {
                    authorRepository.save(author);
                }
            }
            bookRepository.save(bookToSave);
            System.out.println("Libro guardado exitosamente:\n" + bookToSave);
        } else {
            System.out.println("No se encontró el libro \"" + title + "\" en la API de Gutendex.");
        }
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsAliveInYear(Integer year) {
        return authorRepository.findAuthorsAliveInYear(year);
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    @Transactional // Esto asegura que la operación se realice como una única transacción
    public void deleteAllData() {
        // Importante: Eliminar primero los libros para evitar problemas con las claves foráneas
        // si un autor se elimina antes que sus libros.
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        System.out.println("Todos los libros y autores han sido eliminados de la base de datos.");
    }
}
