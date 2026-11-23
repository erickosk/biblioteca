import java.time.LocalDate;
import java.util.ArrayList;

/** Lógica del sistema: registro, búsqueda, préstamos y devoluciones. */
public class Biblioteca {
    private ArrayList<Libro> libros;

    public Biblioteca(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public ArrayList<Libro> getLibros() { return libros; }

    /** Registra un libro nuevo. Devuelve un mensaje con el resultado. */
    public String registrarLibro(String titulo, String autor, String isbn) {
        titulo = titulo.trim();
        autor = autor.trim();
        isbn = isbn.trim();
        if (titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty()) {
            return "Todos los campos son obligatorios.";
        }
        if (titulo.contains("|") || autor.contains("|") || isbn.contains("|")) {
            return "No se permite el carácter '|'.";
        }
        int mayorId = 0;
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                return "Ya existe un libro con ese ISBN.";
            }
            if (l.getId() > mayorId) {
                mayorId = l.getId();
            }
        }
        libros.add(new Libro(mayorId + 1, titulo, autor, isbn));
        return "Libro registrado con ID " + (mayorId + 1) + ".";
    }

    /** Busca libros cuyo título o autor contenga el texto (sin distinguir mayúsculas). */
    public ArrayList<Libro> buscar(String texto) {
        texto = texto.trim().toLowerCase();
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.getTitulo().toLowerCase().contains(texto)
                    || l.getAutor().toLowerCase().contains(texto)) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    /** Devuelve los libros disponibles (true) o los prestados (false). */
    public ArrayList<Libro> filtrarPorEstado(boolean disponibles) {
        ArrayList<Libro> resultado = new ArrayList<>();
        for (Libro l : libros) {
            if (l.isDisponible() == disponibles) {
                resultado.add(l);
            }
        }
        return resultado;
    }

    private Libro buscarPorId(int id) {
        for (Libro l : libros) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    /** Registra el préstamo de un libro. Devuelve un mensaje. */
    public String prestar(int id, String persona) {
        Libro l = buscarPorId(id);
        if (l == null) {
            return "No existe un libro con ese ID.";
        }
        if (persona.trim().isEmpty() || persona.contains("|")) {
            return "Nombre de solicitante no válido.";
        }
        if (!l.isDisponible()) {
            return "'" + l.getTitulo() + "' ya está prestado a " + l.getPrestadoA() + ".";
        }
        l.prestar(persona.trim(), LocalDate.now().toString());
        return "Préstamo registrado: '" + l.getTitulo() + "' -> " + persona.trim() + ".";
    }

    /** Registra la devolución de un libro. Devuelve un mensaje. */
    public String devolver(int id) {
        Libro l = buscarPorId(id);
        if (l == null) {
            return "No existe un libro con ese ID.";
        }
        if (l.isDisponible()) {
            return "Ese libro no estaba prestado.";
        }
        l.devolver();
        return "Devolución registrada: '" + l.getTitulo() + "'.";
    }
}
