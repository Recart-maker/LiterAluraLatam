package com.alura.literatura;

import com.alura.literatura.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.util.InputMismatchException; // Importar para manejar errores de entrada
import java.util.Scanner; // Importar Scanner

@SpringBootApplication
@PropertySource("file:${user.dir}/.env.properties") // Cargar desde raíz del proyecto

public class LiteraturaAppApplication implements CommandLineRunner {

	@Autowired
	private BookController bookController; // Inyecta tu controlador

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in); // Crear el Scanner aquí
		int option = -1;

		while (option != 0) {
			bookController.displayMenu(); // Llama al método que muestra el menú (solo lo imprime)
			try {
				option = scanner.nextInt();
				scanner.nextLine(); // Consumir la nueva línea después del número

				switch (option) {
					case 1:
						System.out.println("Ingrese el título del libro a buscar:");
						String title = scanner.nextLine();
						bookController.searchBookByTitle(title); // Llama al controlador
						break;
					case 2:
						bookController.listAllBooks(); // Llama al controlador
						break;
					case 3:
						bookController.listAllAuthors(); // Llama al controlador
						break;
					case 4:
						System.out.println("Ingrese el año:");
						Integer year = scanner.nextInt();
						scanner.nextLine(); // Consumir la nueva línea
						bookController.listAuthorsAliveInYear(year); // Llama al controlador
						break;
					case 5:
						System.out.println("Ingrese el idioma (ej. es, en, fr, pt):");
						String language = scanner.nextLine();
						bookController.listBooksByLanguage(language); // Llama al controlador
						break;
					case 6: // ¡NUEVO CASE PARA ELIMINAR TODO!
						System.out.println("¿Está seguro de que desea eliminar TODOS los libros y autores? (s/n)");
						String confirmation = scanner.nextLine().trim().toLowerCase();
						if (confirmation.equals("s")) {
							bookController.deleteAllBooksAndAuthors();
						} else {
							System.out.println("Operación de eliminación cancelada.");
						}
						break;
					case 0:
						System.out.println("Saliendo de la aplicación...");
						break;
					default:
						System.out.println("Opción inválida. Intente de nuevo.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número.");
				scanner.nextLine(); // Limpiar el buffer del scanner
				option = -1; // Para que el bucle continúe
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
				e.printStackTrace(); // Para ver la pila completa del error en consola
			}
			// Añadimos la pausa aquí para que el usuario pueda leer la salida
			if (option != 0) {
				System.out.println("\nPresione Enter para continuar...");
				scanner.nextLine();
			}
		}
		scanner.close(); // Cierra el scanner al salir del bucle principal
	}
}