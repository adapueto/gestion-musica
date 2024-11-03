import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Cancion {  //Cancion
    private int id;
    private String titulo;
    private int duracion; // en minutos
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
    public int getIdAlbum() { return idAlbum; }
    public void setIdAlbum(int idAlbum) { this.idAlbum = idAlbum; }
    public int getIdArtista() { return idArtista; }
    public void setIdArtista(int idArtista) { this.idArtista = idArtista; }
    public int getIdGenero() { return idGenero; }
    public void setIdGenero(int idGenero) { this.idGenero = idGenero; }

    @Override
    public String toString() {
        return "Cancion{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracion=" + duracion +
                ", idAlbum=" + idAlbum +
                ", idArtista=" + idArtista +
                ", idGenero=" + idGenero +
                '}';
    }
}

class Album {
    private int id;
    private String titulo;
    private int anioLanzamiento;
    private int idArtista;
    private String generoPrincipal;

    public Album(int id, String titulo, int anioLanzamiento, int idArtista, String generoPrincipal) {
        this.id = id;
        this.titulo = titulo;
        this.anioLanzamiento = anioLanzamiento;
        this.idArtista = idArtista;
        this.generoPrincipal = generoPrincipal;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", anioLanzamiento=" + anioLanzamiento +
                ", idArtista=" + idArtista +
                ", generoPrincipal='" + generoPrincipal + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getGeneroPrincipal() {
        return generoPrincipal;
    }

    public void setGeneroPrincipal(String generoPrincipal) {
        this.generoPrincipal = generoPrincipal;
    }
}

class Artista {
    private int id;
    private String nombre;
    private String fechaNacimiento;
    private String nacionalidad;
    private String generos; 

    public Artista(int id, String nombre, String fechaNacimiento, String nacionalidad, String generos) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.generos = generos;
    }

    

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", generos='" + generos + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getGeneros() {
        return generos;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }
}

class Genero {
    private int id;
    private String nombreGenero;
    private String descripcion;

    public Genero(int id, String nombreGenero, String descripcion) {
        this.id = id;
        this.nombreGenero = nombreGenero;
        this.descripcion = descripcion;
    }

    

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", nombreGenero='" + nombreGenero + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

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
}


public class Basededatos {
    private List<Cancion> canciones;
    private List<Album> albums;
    private List<Artista> artistas;
    private List<Genero> generos;
    private int nextCancionId = 1;
    private int nextAlbumId = 1;
    private int nextArtistaId = 1;
    private int nextGeneroId = 1;

    public Basededatos() {
        canciones = new ArrayList<>();
        albums = new ArrayList<>();
        artistas = new ArrayList<>();
        generos = new ArrayList<>();
    }

    // Métodos para agregar, editar, eliminar y consultar

    public void agregarCancion(String titulo, int duracion, int idAlbum, int idArtista, int idGenero) {
        Cancion nuevaCancion = new Cancion(nextCancionId++, titulo, duracion, idAlbum, idArtista, idGenero);
        canciones.add(nuevaCancion);
    }

    public void editarCancion(int id, String titulo, int duracion, int idAlbum, int idArtista, int idGenero) {
        Optional<Cancion> cancionOpt = canciones.stream().filter(c -> c.getId() == id).findFirst();
        cancionOpt.ifPresent(c -> {
            c.setTitulo(titulo);
            c.setDuracion(duracion);
            c.setIdAlbum(idAlbum);
            c.setIdArtista(idArtista);
            c.setIdGenero(idGenero);
        });
    }

    public void eliminarCancion(int id) {
        canciones.removeIf(c -> c.getId() == id);
    }


    public List<Cancion> consultaCanciones(String titulo, String artista) {
    
        Stream<Cancion> stream = canciones.stream()
                .filter(c -> (c.getTitulo() == null || c.getTitulo().equalsIgnoreCase(titulo)));

        // Filtrar por artista si se proporciona
        if (artista != null) {
            System.out.println("Buscando artista: " + artista);
            Optional<Artista> artisOptional = artistas.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(artista))
                .findFirst();

            if (artisOptional.isPresent()) {
                int idArtistaBuscado = artisOptional.get().getId();
                System.out.println("Artista encontrado: " + artisOptional.get().getNombre() + " con ID: " + idArtistaBuscado);

                // Imprimir IDs de artista de cada canción y comparar
                System.out.println("Comparando ID del artista buscado con IDs de canciones:");
                canciones.forEach(c -> {
                    int idArtistaCancion = c.getIdArtista();
                    System.out.println("Canción: " + c.getTitulo() + ", ID Artista: " + idArtistaCancion);

                    // Comparar IDs
                    if (idArtistaCancion == idArtistaBuscado) {
                        System.out.println(" - Coincidencia encontrada: " + c.getTitulo());
                    }
                });

                // Filtrar las canciones por ID del artista
                //stream = stream.filter(c -> c.getIdArtista() == idArtistaBuscado);
                long cantidadCanciones = stream.filter(c -> c.getIdArtista() == idArtistaBuscado).count();
                System.out.println("Número de canciones encontradas: " + cantidadCanciones);
                    if (cantidadCanciones > 0) {
                        System.out.println("Se encontraron canciones.");
                    } else {
                        System.out.println("No se encontraron canciones.");
                    }

            } else {
                System.out.println("No se encontró el artista: " + artista);
                return Collections.emptyList(); // Si no se encuentra el artista, devuelve lista vacía
            }
        }

        // Filtrar por álbum si se proporciona
        // if (album != null) {
        //     Optional<Album> albumOptional = albums.stream()
        //         .filter(a -> a.getTitulo().equalsIgnoreCase(album))
        //         .findFirst();

        //     if (albumOptional.isPresent()) {
        //         int idAlbum = albumOptional.get().getId();
        //         stream = stream.filter(c -> c.getIdAlbum() == idAlbum);
        //     } else {
        //         return Collections.emptyList(); // Si no se encuentra el álbum, devuelve lista vacía
        //     }
        // }

        // Filtrar por género si se proporciona
        // if (genero != null) {
        //     Optional<Genero> generoOptional = generos.stream()
        //         .filter(g -> g.getNombreGenero().equalsIgnoreCase(genero))
        //         .findFirst();

        //     if (generoOptional.isPresent()) {
        //         int idGenero = generoOptional.get().getId();
        //         stream = stream.filter(c -> c.getIdGenero() == idGenero);
        //     } else {
        //         return Collections.emptyList(); // Si no se encuentra el género, devuelve lista vacía
        //     }
        // }

        // Recoge el resultado final
        return stream.collect(Collectors.toList());
    }


    public void agregarAlbum(String titulo, int anioLanzamiento, int idArtista, String generoPrincipal) {
        Album nuevoAlbum = new Album(nextAlbumId++, titulo, anioLanzamiento, idArtista, generoPrincipal);
        albums.add(nuevoAlbum);
    }

    public List<Album> consultarAlbums() {
        return albums;
    }

    public void agregarArtista(String nombre, String fechaNacimiento, String nacionalidad, String generos) {
        Artista nuevoArtista = new Artista(nextArtistaId++, nombre, fechaNacimiento, nacionalidad, generos);
        artistas.add(nuevoArtista);
    }

    public List<Artista> consultarArtistas() {
        return artistas;
    }

    public void agregarGenero(String nombreGenero, String descripcion) {
        Genero nuevoGenero = new Genero(nextGeneroId++, nombreGenero, descripcion);
        generos.add(nuevoGenero);
    }

    public List<Genero> consultarGeneros() {
        return generos;
    }
}