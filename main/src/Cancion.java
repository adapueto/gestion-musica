public class Cancion {
    private int id;
    private String titulo;
    private int duracion; // en segundos
    private int idAlbum;
    private int idArtista;
    private int idGenero;

    public Cancion(int id, String titulo, int duracion, int idAlbum, int idArtista, int idGenero) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.idAlbum = idAlbum;
        this.idArtista = idArtista;
        this.idGenero = idGenero;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}