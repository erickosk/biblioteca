import java.util.ArrayList;
import java.util.Scanner;

/** Programa principal: menú de consola del sistema de biblioteca. */
public class Main {
    private static Scanner teclado = new Scanner(System.in);

    /** Pide un entero; repite hasta que el usuario escriba uno válido. */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida: escriba un número entero.");
            }
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    /** Imprime una lista de libros en formato de tabla. */
    private static void mostrarTabla(ArrayList<Libro> lista) {
        if (lista.isEmpty()) {
            System.out.println("(sin resultados)");
            return;
        }
        System.out.printf("%-4s%-30s%-22s%-15s%s%n", "ID", "Título", "Autor", "ISBN", "Estado");
        System.out.println("-".repeat(85));
        for (Libro l : lista) {
            String estado = l.isDisponible() ? "Disponible" : "Prestado a " + l.getPrestadoA();
            System.out.printf("%-4d%-30s%-22s%-15s%s%n", l.getId(),
                    recortar(l.getTitulo(), 28), recortar(l.getAutor(), 20), l.getIsbn(), estado);
        }
    }

    private static String recortar(String s, int max) {
        return s.length() > max ? s.substring(0, max) : s;
    }

    private static void mostrarMenu() {
        System.out.println("\n=== BIBLIOTECA ===");
        System.out.println("1. Registrar libro");
        System.out.println("2. Ver catálogo");
        System.out.println("3. Buscar por título o autor");
        System.out.println("4. Registrar préstamo");
        System.out.println("5. Registrar devolución");
        System.out.println("6. Ver disponibles y prestados");
        System.out.println("0. Guardar y salir");
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(Almacenamiento.cargar());
        boolean salir = false;

        while (!salir) {
            mostrarMenu();
            String opcion = leerTexto("Opción: ").trim();

            switch (opcion) {
                case "1":
                    String titulo = leerTexto("Título: ");
                    String autor = leerTexto("Autor: ");
                    String isbn = leerTexto("ISBN: ");
                    System.out.println(biblioteca.registrarLibro(titulo, autor, isbn));
                    Almacenamiento.guardar(biblioteca.getLibros());
                    break;
                case "2":
                    mostrarTabla(biblioteca.getLibros());
                    break;
                case "3":
                    mostrarTabla(biblioteca.buscar(leerTexto("Buscar: ")));
                    break;
                case "4":
                    int idPrestamo = leerEntero("ID del libro: ");
                    String persona = leerTexto("Nombre del solicitante: ");
                    System.out.println(biblioteca.prestar(idPrestamo, persona));
                    Almacenamiento.guardar(biblioteca.getLibros());
                    break;
                case "5":
                    System.out.println(biblioteca.devolver(leerEntero("ID del libro: ")));
                    Almacenamiento.guardar(biblioteca.getLibros());
                    break;
                case "6":
                    System.out.println("\n-- Disponibles --");
                    mostrarTabla(biblioteca.filtrarPorEstado(true));
                    System.out.println("\n-- Prestados --");
                    mostrarTabla(biblioteca.filtrarPorEstado(false));
                    break;
                case "0":
                    Almacenamiento.guardar(biblioteca.getLibros());
                    System.out.println("Datos guardados. ¡Hasta luego!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
