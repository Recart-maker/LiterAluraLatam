package com.alura.literatura.config;

import com.alura.literatura.controller.BookController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Este componente no es estrictamente necesario ya que LiteraturaAppApplication ya implementa CommandLineRunner
// y autowirea BookController. Se incluye como ejemplo de cómo podrías tener una clase de carga de datos separada.
@Component
public class DataLoader implements CommandLineRunner {

    private final BookController bookController;

    public DataLoader(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí no hacemos nada, ya que la lógica del menú se inicia en LiteraturaAppApplication.run()
        // Si tuvieras datos iniciales que cargar en la BD al inicio, irían aquí.
    }
}