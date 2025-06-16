# LiterAluraLatam

Aplicación Java para gestionar información de libros y autores obtenidos desde la API de Gutendex (https://gutendex.com/books). Permite buscar libros por título, listar libros registrados, listar autores, buscar autores vivos en un año específico y filtrar libros por idioma.

Características principales
Conexión con API pública de Gutendex (sin API key)

Almacenamiento en base de datos PostgreSQL

Menú interactivo por consola

Búsquedas flexibles e insensibles a mayúsculas/minúsculas

Gestión segura de credenciales

Tecnologías utilizadas
Java 17

Spring Boot 3.3.0

Spring Data JPA

PostgreSQL

Maven

Prerrequisitos
Java 17 o superior

Maven 3.8.6 o superior

PostgreSQL 14 o superior

Cuenta en GitHub (opcional para contribución)

Configuración
Clonar el repositorio:

bash
git clone https://github.com/tu-usuario/literatura_db.git
cd literatura_db
Configurar base de datos:

Crear una base de datos en PostgreSQL:

sql
CREATE DATABASE literatura_db;
Configurar variables de entorno:

bash
# Copiar el archivo de ejemplo
cp env.properties.example .env.properties

# Editar con tus credenciales
nano .env.properties
Ejemplo de contenido para .env.properties:

properties
DATABASE_URL=jdbc:postgresql://localhost:5432/literatura_db
DB_USER=tu_usuario
DB_PASS=tu_contraseña
SERVER_PORT=8081
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
Ejecución
Iniciar la aplicación:

bash
mvn spring-boot:run
Usar la aplicación:
Al iniciar, se mostrará un menú interactivo con las siguientes opciones:

text
Elija una opción:
1- Buscar libro por título
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en un año
5- Listar libros por idioma
0- Salir
Opción: 
Uso de la aplicación
Buscar libro por título:

Ingresa palabras clave del título

Ejemplo: "quijote" encontrará "Don Quijote de la Mancha"

Listar libros registrados:

Muestra todos los libros almacenados en la DB

Listar autores registrados:

Muestra todos los autores con sus años de nacimiento y muerte

Autores vivos en un año:

Ingresa un año (ej: 1600) para ver autores vivos en ese año

Libros por idioma:

Ingresa código ISO de idioma (ej: "es" para español, "en" para inglés)

Estructura del proyecto
text
literatura_db/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── literatura/
│   │   │               ├── model/       # Entidades JPA
│   │   │               ├── repository/  # Repositorios Spring Data
│   │   │               ├── service/     # Lógica de negocio
│   │   │               └── LiteraturaApplication.java
│   │   └── resources/
│   │       └── application.properties   # Configuración principal
│   └── test/                           # Pruebas unitarias
├── .env.properties                     # Credenciales (local)
├── env.properties.example              # Plantilla de configuración
├── .gitignore                          # Archivos ignorados por Git
├── pom.xml                             # Configuración de Maven
└── README.md                           # Este archivo
Contribución
Haz un fork del proyecto

Crea una rama para tu feature (git checkout -b feature/nueva-funcionalidad)

Realiza tus cambios y haz commit (git commit -m 'Agrega nueva funcionalidad')

Push a la rama (git push origin feature/nueva-funcionalidad)

Abre un Pull Request

Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

Nota: Nunca compartas tu archivo .env.properties ni lo subas a repositorios públicos.
