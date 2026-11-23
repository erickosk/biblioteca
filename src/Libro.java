/**
 * Clase Libro: representa un libro del catálogo y sabe
 * convertirse a texto (para guardar) y leerse desde texto (para cargar).
 */
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponible;
    private String prestadoA;      // vacío si está disponible
    private String fechaPrestamo;  // vacío si está disponible

    public Libro(int id, String titulo, String autor, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = true;
        this.prestadoA = "";
        this.fechaPrestamo = "";
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public boolean isDisponible() { return disponible; }
    public String getPrestadoA() { return prestadoA; }
    public String getFechaPrestamo() { return fechaPrestamo; }

    /** Marca el libro como prestado. */
    public void prestar(String persona, String fecha) {
        this.disponible = false;
        this.prestadoA = persona;
        this.fechaPrestamo = fecha;
    }

    /** Marca el libro como disponible otra vez. */
    public void devolver() {
        this.disponible = true;
        this.prestadoA = "";
        this.fechaPrestamo = "";
    }

    /** Convierte el libro a una línea de texto separada por "|". */
    public String aLinea() {
        return id + "|" + titulo + "|" + autor + "|" + isbn + "|" + disponible
                + "|" + prestadoA + "|" + fechaPrestamo;
    }

    /** Crea un libro a partir de una línea guardada; devuelve null si la línea es inválida. */
    public static Libro desdeLinea(String linea) {
        String[] p = linea.split("\\|", -1);
        if (p.length != 7) {
            return null;
        }
        try {
            Libro l = new Libro(Integer.parseInt(p[0]), p[1], p[2], p[3]);
            if (p[4].equals("false")) {
                l.prestar(p[5], p[6]);
            }
            return l;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
