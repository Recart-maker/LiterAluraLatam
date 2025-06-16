package com.alura.literatura.repository; // Asegúrate de que el paquete sea correcto

import com.alura.literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // Esta anotación solo va una vez en la interfaz
public interface AuthorRepository extends JpaRepository<Author, Long> {

    // Método para buscar por nombre ignorando mayúsculas/minúsculas
    Optional<Author> findByNameIgnoreCase(String name);

    // Método para buscar autores vivos en un año determinado
    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAuthorsAliveInYear(Integer year);

    // Método para buscar autor por nombre y cargar sus libros de forma eagerly
    @Query("SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.name = :name")
    Optional<Author> findByNameWithBooks(@Param("name") String name);
}