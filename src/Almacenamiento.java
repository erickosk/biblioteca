import java.io.*;
import java.util.ArrayList;

/** Manejo de archivos: guarda y carga el catálogo en data/biblioteca.txt */
public class Almacenamiento {
    private static final String RUTA = "data/biblioteca.txt";

    /** Lee el archivo y devuelve la lista de libros (vacía si no existe). */
    public static ArrayList<Libro> cargar() {
        ArrayList<Libro> libros = new ArrayList<>();
        File archivo = new File(RUTA);
        if (!archivo.exists()) {
            return libros;
        }
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {   // ciclo: una línea por libro
                Libro l = Libro.desdeLinea(linea);
                if (l != null) {
                    libros.add(l);
                }
            }
        } catch (IOException e) {
            System.out.println("Aviso: no se pudo leer el archivo. Se inicia vacío.");
        }
        return libros;
    }

    /** Escribe todos los libros en el archivo, una línea por libro. */
    public static void guardar(ArrayList<Libro> libros) {
        new File("data").mkdirs();
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA))) {
            for (Libro l : libros) {
                escritor.println(l.aLinea());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
