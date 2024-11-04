public class Genero {
    private int id;
    private String nombreGenero;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Genero(int id, String nombreGenero, String descripcion) {
        this.id = id;
        this.nombreGenero = nombreGenero;
        this.descripcion = descripcion;
    }
}