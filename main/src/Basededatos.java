import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BaseDeDatos {
    private List<Cancion> canciones = new ArrayList<>();
    private List<Album> albums = new ArrayList<>();
    private List<Artista> artistas = new ArrayList<>();
    private List<Genero> generos = new ArrayList<>();

    public BaseDeDatos() {
        cargarDatosEjemplo(); // Llama a cargar datos de ejemplo automáticamente
    }

    // Metodo para cargar datos de ejemplo directamente
    private void cargarDatosEjemplo() {
        // Ejemplos de Géneros
        Genero rock = new Genero(1, "Rock", "Género musical popular que se originó en los años 50.");
        Genero pop = new Genero(2, "Pop", "Género musical popular que se centra en el atractivo general.");
        generos.add(rock);
        generos.add(pop);

        // Ejemplos de Artistas
        Artista beatles = new Artista(1, "The Beatles", "1960-07-06", "Reino Unido", "Rock");
        Artista michaelJackson = new Artista(2, "Michael Jackson", "1958-08-29", "EE.UU.", "Pop");
        artistas.add(beatles);
        artistas.add(michaelJackson);

        // Ejemplos de Álbumes
        Album abbeyRoad = new Album(1, "Abbey Road", 1969, 1, "Rock");
        Album thriller = new Album(2, "Thriller", 1982, 2, "Pop");
        albums.add(abbeyRoad);
        albums.add(thriller);

        // Ejemplos de Canciones
        Cancion comeTogether = new Cancion(1, "Come Together", 259, 1, 1, 1); // Álbum "Abbey Road", Artista "The Beatles", Género "Rock"
        Cancion billieJean = new Cancion(2, "Billie Jean", 294, 2, 2, 2); // Álbum "Thriller", Artista "Michael Jackson", Género "Pop"
        canciones.add(comeTogether);
        canciones.add(billieJean);

        System.out.println("Datos de ejemplo cargados en el sistema.");
    }

    //Case 1
    public void agregarCancion(String titulo, int duracion, int idAlbum, int idArtista, int idGenero) {
        if (!existeAlbum(idAlbum)) {
            System.out.println("Error: El álbum con ID " + idAlbum + " no existe.");
            return;
        }
        if (!existeArtista(idArtista)) {
            System.out.println("Error: El artista con ID " + idArtista + " no existe.");
            return;
        }
        if (!existeGenero(idGenero)) {
            System.out.println("Error: El género con ID " + idGenero + " no existe.");
            return;
        }

        if (duracion <= 0) {
            System.out.println("Error: La duración de la canción debe ser un valor positivo.");
            return;
        }

        // Si todas las verificaciones son correctas, se crea y agrega la canción
        int cancionId = canciones.size() + 1;
        Cancion nuevaCancion = new Cancion(cancionId, titulo, duracion, idAlbum, idArtista, idGenero);
        canciones.add(nuevaCancion);
        System.out.println("Canción agregada correctamente: " + titulo);
    }


    public void agregarArtista(String nombre, String fechaNacimiento, String nacionalidad, String generos) {
        int artistaId = artistas.size() + 1;
        Artista nuevoArtista = new Artista(artistaId, nombre, fechaNacimiento, nacionalidad, generos);
        artistas.add(nuevoArtista);
        System.out.println("Artista agregado/a correctamente: " + nombre);
    }

        public void agregarGenero(String nombreGenero, String descripcion) {
        int generoId = generos.size() + 1;
        Genero nuevoGenero = new Genero(generoId, nombreGenero, descripcion);
        generos.add(nuevoGenero);
        System.out.println("Genero agregado correctamente: " + nombreGenero);
    }

    public List<Cancion> buscarCanciones(String titulo, String nombreArtista, String nombreAlbum, String nombreGenero) {
        List<Cancion> resultado = canciones.stream()
                .filter(cancion -> (titulo == null || titulo.isEmpty() || cancion.getTitulo().equalsIgnoreCase(titulo)))
                .filter(cancion -> {
                    if (nombreArtista == null || nombreArtista.isEmpty()) return true;
                    Artista artista = obtenerArtistaPorId(cancion.getIdArtista());
                    return artista != null && artista.getNombre().equalsIgnoreCase(nombreArtista);
                })
                .filter(cancion -> {
                    if (nombreAlbum == null || nombreAlbum.isEmpty()) return true;
                    Album album = obtenerAlbumPorId(cancion.getIdAlbum());
                    return album != null && album.getTitulo().equalsIgnoreCase(nombreAlbum);
                })
                .filter(cancion -> {
                    if (nombreGenero == null || nombreGenero.isEmpty()) return true;
                    Genero genero = obtenerGeneroPorId(cancion.getIdGenero());
                    return genero != null && genero.getNombreGenero().equalsIgnoreCase(nombreGenero);
                })
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            System.out.println("No se encontraron coincidencias");
        }
        return resultado;
    }

    public boolean editarCancion(Integer id, String titulo, Integer nuevaDuracion, Integer nuevoIdAlbum, Integer nuevoIdArtista, Integer nuevoIdGenero) {
        Cancion cancion = null;

        // Buscar la canción por ID si se proporciona
        if (id != null) {
            cancion = canciones.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        } else if (titulo != null && !titulo.isEmpty()) { // Buscar por título si no se proporciona ID
            cancion = canciones.stream().filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
        }

        if (cancion == null) {
            System.out.println("Canción no encontrada.");
            return false;
        }

        // Actualizar los atributos proporcionados
        if (nuevaDuracion != null) {
            cancion.setDuracion(nuevaDuracion);
        }
        if (nuevoIdAlbum != null && existeAlbum(nuevoIdAlbum)) {
            cancion.setIdAlbum(nuevoIdAlbum);
        } else if (nuevoIdAlbum != null) {
            System.out.println("Error: El álbum con ID " + nuevoIdAlbum + " no existe.");
            return false;
        }
        if (nuevoIdArtista != null && existeArtista(nuevoIdArtista)) {
            cancion.setIdArtista(nuevoIdArtista);
        } else if (nuevoIdArtista != null) {
            System.out.println("Error: El artista con ID " + nuevoIdArtista + " no existe.");
            return false;
        }
        if (nuevoIdGenero != null && existeGenero(nuevoIdGenero)) {
            cancion.setIdGenero(nuevoIdGenero);
        } else if (nuevoIdGenero != null) {
            System.out.println("Error: El género con ID " + nuevoIdGenero + " no existe.");
            return false;
        }

        System.out.println("Canción actualizada correctamente.");
        return true;
    }

    public boolean eliminarCancion(Integer id, String titulo) {
        Cancion cancionAEliminar = null;

        // Buscar la canción por ID o por título
        if (id != null) {
            cancionAEliminar = canciones.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        } else if (titulo != null && !titulo.isEmpty()) {
            cancionAEliminar = canciones.stream().filter(c -> c.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
        }

        // Si la canción existe, se elimina de la lista
        if (cancionAEliminar != null) {
            canciones.remove(cancionAEliminar);
            System.out.println("Canción eliminada correctamente: " + cancionAEliminar.getTitulo());
            return true;
        } else {
            System.out.println("Canción no encontrada.");
            return false;
        }
    }

    public boolean agregarAlbum(String titulo, int anioLanzamiento, int idArtista, String generoPrincipal) {
        // Verificar si el artista existe en la lista de artistas
        if (!existeArtista(idArtista)) {
            System.out.println("Error: El artista con ID " + idArtista + " no existe.");
            return false;
        }

        // Crear el nuevo álbum y agregarlo a la lista de álbumes
        int idAlbum = albums.size() + 1; // Generar ID del álbum como siguiente índice
        Album nuevoAlbum = new Album(idAlbum, titulo, anioLanzamiento, idArtista, generoPrincipal);
        albums.add(nuevoAlbum);
        System.out.println("Álbum agregado correctamente: " + titulo);
        return true;
    }

    public List<Album> consultarAlbum(String titulo, Integer anioLanzamiento, String nombreArtista, String generoPrincipal) {
        return albums.stream()
                .filter(album -> titulo == null || titulo.isEmpty() || album.getTitulo().equalsIgnoreCase(titulo))
                .filter(album -> anioLanzamiento == null || album.getAnioLanzamiento() == anioLanzamiento)
                .filter(album -> {
                    if (nombreArtista == null || nombreArtista.isEmpty()) return true;
                    Artista artista = obtenerArtistaPorId(album.getIdArtista());
                    return artista != null && artista.getNombre().equalsIgnoreCase(nombreArtista);
                })
                .filter(album -> generoPrincipal == null || generoPrincipal.isEmpty() || album.getGeneroPrincipal().equalsIgnoreCase(generoPrincipal))
                .collect(Collectors.toList());
    }

    public List<Artista> consultarArtistas(String nombre, String nacionalidad, String generoPrincipal) {
        return artistas.stream()
                .filter(artista -> nombre == null || nombre.isEmpty() || artista.getNombre().equalsIgnoreCase(nombre))
                .filter(artista -> nacionalidad == null || nacionalidad.isEmpty() || artista.getNacionalidad().equalsIgnoreCase(nacionalidad))
                .filter(artista -> generoPrincipal == null || generoPrincipal.isEmpty() || artista.getGeneros().equalsIgnoreCase(generoPrincipal))
                .collect(Collectors.toList());
    }

    public boolean editarAlbum(Integer id, String titulo, String nuevoTitulo, Integer nuevoAnioLanzamiento, String nuevoGeneroPrincipal) {
        Album album = null;

        // Buscar el álbum por ID o por título
        if (id != null) {
            album = albums.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        } else if (titulo != null && !titulo.isEmpty()) {
            album = albums.stream().filter(a -> a.getTitulo().equalsIgnoreCase(titulo)).findFirst().orElse(null);
        }

        if (album == null) {
            System.out.println("Error: No se encontró un álbum con el ID o título especificado.");
            return false;
        }

        // Actualizar los detalles del álbum
        if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) {
            album.setTitulo(nuevoTitulo);
        }
        if (nuevoAnioLanzamiento != null) {
            album.setAnioLanzamiento(nuevoAnioLanzamiento);
        }
        if (nuevoGeneroPrincipal != null && !nuevoGeneroPrincipal.isEmpty()) {
            album.setGeneroPrincipal(nuevoGeneroPrincipal);
        }

        System.out.println("Álbum actualizado correctamente: " + album.getTitulo());
        return true;
    }

    // Metodo para editar un artista por ID o nombre
    public boolean editarArtista(Integer id, String nombre, String nuevoNombre, String nuevaFechaNacimiento, String nuevaNacionalidad, String nuevoGenero) {
        Artista artista = null;

        // Buscar el artista por ID o por nombre
        if (id != null) {
            artista = artistas.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        } else if (nombre != null && !nombre.isEmpty()) {
            artista = artistas.stream().filter(a -> a.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
        }

        if (artista == null) {
            System.out.println("Error: No se encontró un artista con el ID o nombre especificado.");
            return false;
        }

        // Actualizar los detalles del artista
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            artista.setNombre(nuevoNombre);
        }
        if (nuevaFechaNacimiento != null && !nuevaFechaNacimiento.isEmpty()) {
            artista.setFechaNacimiento(nuevaFechaNacimiento);
        }
        if (nuevaNacionalidad != null && !nuevaNacionalidad.isEmpty()) {
            artista.setNacionalidad(nuevaNacionalidad);
        }
        if (nuevoGenero != null && !nuevoGenero.isEmpty()) {
            artista.setGeneros(nuevoGenero);
        }

        System.out.println("Artista actualizado correctamente: " + artista.getNombre());
        return true;
    }

    public List<Genero> consultarGeneros() {
        return generos;
    }

    public List<Cancion> listarCanciones(String filtroTitulo, String filtroGenero, Integer filtroDuracion, boolean ordenarPorTitulo) {
        return canciones.stream()
                .filter(c -> filtroTitulo == null || filtroTitulo.isEmpty() || c.getTitulo().equalsIgnoreCase(filtroTitulo))
                .filter(c -> {
                    if (filtroGenero == null || filtroGenero.isEmpty()) return true;
                    Genero genero = obtenerGeneroPorId(c.getIdGenero()); // Usamos el metodo directo
                    return genero != null && genero.getNombreGenero().equalsIgnoreCase(filtroGenero);
                })
                .filter(c -> filtroDuracion == null || c.getDuracion() == filtroDuracion)
                .sorted(ordenarPorTitulo ? Comparator.comparing(Cancion::getTitulo) : Comparator.comparingInt(Cancion::getId))
                .collect(Collectors.toList());

    }

    // Metodo para listar álbumes con filtros y ordenación
    public List<Album> listarAlbumes(String filtroTitulo, String filtroGeneroPrincipal, Integer filtroAnioLanzamiento, String filtroNombreArtista, boolean ordenarPorTitulo) {
        return albums.stream()
                .filter(album -> filtroTitulo == null || filtroTitulo.isEmpty() || album.getTitulo().equalsIgnoreCase(filtroTitulo))
                .filter(album -> filtroAnioLanzamiento == null || album.getAnioLanzamiento() == filtroAnioLanzamiento)
                .filter(album -> {
                    if (filtroNombreArtista == null || filtroNombreArtista.isEmpty()) return true;
                    Artista artista = obtenerArtistaPorId(album.getIdArtista());
                    return artista != null && artista.getNombre().equalsIgnoreCase(filtroNombreArtista);
                })
                .filter(album -> filtroGeneroPrincipal == null || filtroGeneroPrincipal.isEmpty() || album.getGeneroPrincipal().equalsIgnoreCase(filtroGeneroPrincipal))
                .sorted(ordenarPorTitulo ? Comparator.comparing(Album::getTitulo) : Comparator.comparingInt(Album::getAnioLanzamiento))
                .collect(Collectors.toList());
    }

    // Método para listar artistas con filtros y ordenación
    public List<Artista> listarArtistas(String filtroNombre, String filtroNacionalidad, String filtroGenero, boolean ordenarPorNombre) {
        return artistas.stream()
                .filter(a -> filtroNombre == null || filtroNombre.isEmpty() || a.getNombre().equalsIgnoreCase(filtroNombre))
                .filter(a -> filtroNacionalidad == null || filtroNacionalidad.isEmpty() || a.getNacionalidad().equalsIgnoreCase(filtroNacionalidad))
                .filter(a -> filtroGenero == null || filtroGenero.isEmpty() || a.getGeneros().equalsIgnoreCase(filtroGenero))
                .sorted(ordenarPorNombre ? Comparator.comparing(Artista::getNombre) : Comparator.comparingInt(Artista::getId))
                .collect(Collectors.toList());
    }

    // Métodos auxiliares para obtener entidades por ID
    public Artista obtenerArtistaPorId(int idArtista) {
        return artistas.stream()
                .filter(artista -> artista.getId() == idArtista)
                .findFirst()
                .orElse(null);
    }

    public Album obtenerAlbumPorId(int idAlbum) {
        return albums.stream()
                .filter(album -> album.getId() == idAlbum)
                .findFirst()
                .orElse(null);
    }

    public Genero obtenerGeneroPorId(int idGenero) {
        return generos.stream()
                .filter(genero -> genero.getId() == idGenero)
                .findFirst()
                .orElse(null);
    }
    // Métodos de verificación de FK
    public boolean existeAlbum(int idAlbum) {
        return albums.stream().anyMatch(album -> album.getId() == idAlbum);
    }

    public boolean existeArtista(int idArtista) {
        return artistas.stream().anyMatch(artista -> artista.getId() == idArtista);
    }

    public boolean existeGenero(int idGenero) {
        return generos.stream().anyMatch(genero -> genero.getId() == idGenero);
    }
}