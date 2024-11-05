import java.util.List;
import java.util.Scanner;

public class GestorMusica {
    private BaseDeDatos bd;

    public GestorMusica(BaseDeDatos bd) {
        this.bd = bd;
    }

    public void agregarCancion(Scanner scanner) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Duración (en segundos): ");
        int duracion = EntradaValidator.validationInt(scanner);

        System.out.print("ID del Álbum: ");
        int idAlbum = EntradaValidator.validationInt(scanner);
        System.out.print("ID del Artista: ");
        int idArtista = EntradaValidator.validationInt(scanner);

        System.out.print("ID del Género: ");
        int idGenero = EntradaValidator.validationInt(scanner);
        bd.agregarCancion(titulo, duracion, idAlbum, idArtista, idGenero);
    }

    public void agregarArtista(Scanner scanner){
        System.out.print("Nombre del Artista: ");
        String nombreArtista = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (dd-MM-yyyy): ");
        String fechaNacimiento = EntradaValidator.pedirFechaNacimiento(scanner);
        System.out.print("Nacionalidad: ");
        String nacionalidad = EntradaValidator.pedirNacionalidad(scanner);
        System.out.print("Géneros: ");
        String generosArtista = scanner.nextLine();
        bd.agregarArtista(nombreArtista, fechaNacimiento, nacionalidad, generosArtista);
    }

    public void agregarAlbum(Scanner scanner){
        System.out.print("Título del Álbum: ");
        String tituloAlbum = scanner.nextLine();
        System.out.print("Año de Lanzamiento: ");
        int anioLanzamiento = EntradaValidator.validationInt(scanner, true);
        System.out.print("ID del Artista: ");
        int idArtistaAlbum = EntradaValidator.validationInt(scanner);
        System.out.print("Género Principal: ");
        String generoPrincipal = scanner.next();
        bd.agregarAlbum(tituloAlbum, anioLanzamiento, idArtistaAlbum, generoPrincipal);
    }

    public void agregarGenero(Scanner scanner){
        System.out.print("Nombre del Género: ");
        String nombreGenero = scanner.nextLine();
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        bd.agregarGenero(nombreGenero, descripcion);
    }

    public void consultarCanciones(Scanner scanner){
        System.out.println("Ingresa los criterios de búsqueda (deja en blanco para ignorar):");
        System.out.print("Título de la canción: ");
        String tituloBusqueda = scanner.nextLine();
        System.out.print("Nombre del artista: ");
        String nombreArtistaConsulta = scanner.nextLine();
        System.out.print("Nombre del álbum: ");
        String nombreAlbumConsulta = scanner.nextLine();
        System.out.print("Nombre del género: ");
        String nombreGeneroConsulta = scanner.nextLine();

        bd.buscarCanciones(tituloBusqueda, nombreArtistaConsulta, nombreAlbumConsulta, nombreGeneroConsulta).forEach(cancion ->
                System.out.println("Canción encontrada: " + cancion.getTitulo() +
                                    " Duracion:" + cancion.getDuracion() + " segundos" +
                                    " Artista: " + bd.obtenerArtistaPorId(cancion.getIdArtista()).getNombre() +
                                    " Album: " + bd.obtenerAlbumPorId(cancion.getIdAlbum()).getTitulo() +
                                    " Genero: " + bd.obtenerGeneroPorId(cancion.getIdGenero()).getNombreGenero())
        );

    }

    public void consultarAlbum(Scanner scanner){
        System.out.println("Ingresa los criterios de búsqueda (deja en blanco para ignorar):");
        System.out.print("Título del álbum: ");
        String tituloAlbumConsulta = scanner.nextLine();
        System.out.print("Año de lanzamiento: ");
        Integer anioLanzamientoConsulta = EntradaValidator.validationInt(scanner, true, true);

        System.out.print("Nombre del artista: ");
        String ArtistaConsulta = scanner.nextLine();

        System.out.print("Género principal: ");
        String generoPrincipalConsulta = scanner.nextLine();

        List<Album> resultados = bd.consultarAlbum(tituloAlbumConsulta, anioLanzamientoConsulta, ArtistaConsulta, generoPrincipalConsulta);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron álbumes con los criterios especificados.");
        } else {
            resultados.forEach(album -> System.out.println("Álbum encontrado: " + album.getTitulo() +
                    " - Artista: " + bd.obtenerArtistaPorId(album.getIdArtista()).getNombre() +
                    " - Año: " + album.getAnioLanzamiento() +
                    " - Género: " + album.getGeneroPrincipal()));
        }
        
    }

    public void consultarArtista(Scanner scanner){
        System.out.println("Ingresa los criterios de búsqueda para el artista (deja en blanco para ignorar):");
        System.out.print("Nombre del artista: ");
        String ArtistaConsultanombre = scanner.nextLine();
        
        System.out.print("Nacionalidad del artista: ");
        String nacionalidadArtista = EntradaValidator.pedirNacionalidad(scanner);

        System.out.print("Género principal del artista: ");
        String generoArtista = scanner.nextLine();

        List<Artista> resultadosArtista = bd.consultarArtistas(ArtistaConsultanombre, nacionalidadArtista, generoArtista);

        if (resultadosArtista.isEmpty()) {
            System.out.println("No se encontraron artistas con los criterios especificados.");
        } else {
            resultadosArtista.forEach(artista -> System.out.println("Artista encontrado: " + artista.getNombre() +
                    " - Fecha Nacimiento: " + artista.getFechaNacimiento() +
                    " - Nacionalidad: " + artista.getNacionalidad() +
                    " - Género: " + artista.getGeneros()));
        }
        
    }

    public void consultarGenero(Scanner scanner){
        System.out.println("Géneros:");
        bd.consultarGeneros().forEach(genero -> System.out.println("Artista encontrado: " + genero.getNombreGenero() +
                    " -Descripcion: " + genero.getDescripcion()));
        
    }

    public void eliminarCancion(Scanner scanner){
        System.out.print("Ingresa el ID de la canción (o deja en blanco para usar el título): ");
        Integer eliminarId = EntradaValidator.validationInt(scanner, false, true);
        String eliminarTitulo = null;  // Declarar una variable para el título

        // Comprobar si el ID está vacío
        if (eliminarId == null) {
            System.out.print("Ingresa el título de la canción (deja en blanco si usas el ID): ");
            eliminarTitulo = scanner.nextLine(); 
        } 

        bd.eliminarCancion(eliminarId, eliminarTitulo);
        
    }

    public void modificarCancion(Scanner scanner){
        System.out.print("Ingresa el ID de la canción (o deja en blanco para usar el título): ");
        Integer id = EntradaValidator.validationInt(scanner, false, true);


        String tituloEditar = null;  // Declarar una variable para el título

        // Comprobar si el ID está vacío
        if (id == null) {
            System.out.print("Ingresa el título de la canción (deja en blanco si usas el ID): ");
            tituloEditar = scanner.nextLine(); 
        } 

        System.out.print("Nueva duración (deja en blanco para no cambiar): ");
        Integer nuevaDuracion = EntradaValidator.validationInt(scanner, false, true);

        System.out.print("Nuevo ID del álbum (deja en blanco para no cambiar): ");
        Integer nuevoIdAlbum = EntradaValidator.validationInt(scanner, false,  true);

        System.out.print("Nuevo ID del artista (deja en blanco para no cambiar): ");
        Integer nuevoIdArtista = EntradaValidator.validationInt(scanner, false,  true);

        System.out.print("Nuevo ID del género (deja en blanco para no cambiar): ");
        Integer nuevoIdGenero = EntradaValidator.validationInt(scanner, false,  true);

        bd.editarCancion(id, tituloEditar, nuevaDuracion, nuevoIdAlbum, nuevoIdArtista, nuevoIdGenero);
        
    }

    public void modificarAlbum(Scanner scanner){
        System.out.print("Ingresa el ID del álbum (o deja en blanco para usar el título): ");
        Integer idAlbumEdit = EntradaValidator.validationInt(scanner, false, true);

        // Leer el título del álbum si no se usa ID
        String tituloAlbumEdit = null;  // Declarar la variable para el título
        if (idAlbumEdit == null) {
            System.out.print("Ingresa el título del álbum (deja en blanco si usas el ID): ");
            tituloAlbumEdit = scanner.nextLine();  // Leer el título si el ID está vacío
        }

        // Leer el nuevo título del álbum
        System.out.print("Nuevo título del álbum (deja en blanco para no cambiar): ");
        String nuevoTituloAlbum = scanner.nextLine();

        // Leer el nuevo año de lanzamiento
        System.out.print("Nuevo año de lanzamiento (deja en blanco para no cambiar): ");
        Integer nuevoAnioLanzamiento = EntradaValidator.validationInt(scanner, true, true);

        // Leer el nuevo género principal
        System.out.print("Nuevo género principal del álbum (deja en blanco para no cambiar): ");
        String nuevoGeneroPrincipal = scanner.nextLine();

        // Llamar al método para editar el álbum
        bd.editarAlbum(idAlbumEdit, tituloAlbumEdit, nuevoTituloAlbum, nuevoAnioLanzamiento, nuevoGeneroPrincipal);
        
    }

    public void modificarArtista(Scanner scanner){
        System.out.print("Ingresa el ID del artista (o deja en blanco para usar el nombre): ");
        Integer idArtistaEdit = EntradaValidator.validationInt(scanner, false, true);

        // Leer el título del álbum si no se usa ID
        String nombreArtistaEdit = null;  // Declarar la variable para el título
        if (idArtistaEdit == null) {
            System.out.print("Ingresa el título del álbum (deja en blanco si usas el ID): ");
            nombreArtistaEdit = scanner.nextLine();  // Leer el título si el ID está vacío
        }
 
        
        System.out.print("Nuevo nombre del artista (deja en blanco para no cambiar): ");
        String nuevoNombreArtista = scanner.nextLine();

        System.out.print("Nueva fecha de nacimiento del artista ((dd-MM-yyyy, deja en blanco para no cambiar): ");
        String nuevaFechaNacimiento = EntradaValidator.pedirFechaNacimiento(scanner);

        System.out.print("Nueva nacionalidad del artista (deja en blanco para no cambiar): ");
        String nuevaNacionalidad = EntradaValidator.pedirNacionalidad(scanner);

        System.out.print("Nuevo género principal del artista (deja en blanco para no cambiar): ");
        String nuevoGeneroArtista = scanner.nextLine();

        bd.editarArtista(idArtistaEdit, nombreArtistaEdit, nuevoNombreArtista, nuevaFechaNacimiento, nuevaNacionalidad, nuevoGeneroArtista);
        
    }


}