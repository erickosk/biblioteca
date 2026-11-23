# Manual de usuario – Sistema de Biblioteca (Java)

## 1. Requisitos
Java JDK 11 o superior (comando `javac` disponible).

## 2. Compilar y ejecutar
Desde la carpeta del proyecto:
- Compilar: `javac -encoding UTF-8 -d out src/*.java`
- Ejecutar: `java -Dfile.encoding=UTF-8 -cp out Main`
(En Windows, si los acentos se ven mal, ejecute antes `chcp 65001`.)

## 3. Menú
| Opción | Qué hace |
|---|---|
| 1. Registrar libro | Solicita título, autor e ISBN (único). Asigna un ID automático. |
| 2. Ver catálogo | Tabla con todos los libros y su estado. |
| 3. Buscar | Escriba parte del título o del autor (no distingue mayúsculas). |
| 4. Registrar préstamo | Indique el ID del libro y el nombre de quien lo lleva. Se guarda la fecha. |
| 5. Registrar devolución | Indique el ID del libro prestado. |
| 6. Disponibles y prestados | Dos listas separadas. |
| 0. Guardar y salir | Guarda y cierra el programa. |

## 4. Mensajes frecuentes
- "Ya existe un libro con ese ISBN": el ISBN debe ser único.
- "ya está prestado a ...": espere la devolución.
- "Ese libro no estaba prestado": revise el ID.

## 5. Datos
Se guardan automáticamente en `data/biblioteca.txt` (una línea por libro). Haga copia de ese archivo como respaldo.
(Agregue capturas de pantalla de cada opción para completar las 3 páginas.)
