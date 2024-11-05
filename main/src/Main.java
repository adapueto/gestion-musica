import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDeDatos bd = new BaseDeDatos();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Sistema de Gestión de Música");
            System.out.println("1. Agregar Canción");
            System.out.println("2. Consultar Canciones");
            System.out.println("3. Editar Canción");
            System.out.println("4. Eliminar Canción");
            System.out.println("5. Agregar Álbum");
            System.out.println("6. Consultar Álbumes");
            System.out.println("7. Agregar Artista");
            System.out.println("8. Consultar Artistas");
            System.out.println("9. Editar Album");
            System.out.println("10. Editar Artista");
            System.out.println("11. Agregar Género");
            System.out.println("12. Consultar Géneros");
            System.out.println("13. Listas de todo");
            System.out.println("14. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
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
                    break;

                case 2:
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
                    break;

                case 3:
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
                    Integer nuevoIdAlbum = EntradaValidator.validationInt(scanner, false, true);

                    System.out.print("Nuevo ID del artista (deja en blanco para no cambiar): ");
                    Integer nuevoIdArtista = EntradaValidator.validationInt(scanner, false, true);

                    System.out.print("Nuevo ID del género (deja en blanco para no cambiar): ");
                    Integer nuevoIdGenero = EntradaValidator.validationInt(scanner, false, true);

                    bd.editarCancion(id, tituloEditar, nuevaDuracion, nuevoIdAlbum, nuevoIdArtista, nuevoIdGenero);
                    break;

                case 4:
                    System.out.print("Ingresa el ID de la canción (o deja en blanco para usar el título): ");
                    Integer eliminarId = EntradaValidator.validationInt(scanner, false, true);
                    String eliminarTitulo = null;  // Declarar una variable para el título

                    // Comprobar si el ID está vacío
                    if (eliminarId == null) {
                        System.out.print("Ingresa el título de la canción (deja en blanco si usas el ID): ");
                        eliminarTitulo = scanner.nextLine(); 
                    } 

                    bd.eliminarCancion(eliminarId, eliminarTitulo);
                    break;

                case 5:
                    System.out.print("Título del Álbum: ");
                    String tituloAlbum = scanner.nextLine();
                    System.out.print("Año de Lanzamiento: ");
                    int anioLanzamiento = EntradaValidator.validationInt(scanner, true);
                    System.out.print("ID del Artista: ");
                    int idArtistaAlbum = EntradaValidator.validationInt(scanner);
                    System.out.print("Género Principal: ");
                    String generoPrincipal = scanner.next();
                    bd.agregarAlbum(tituloAlbum, anioLanzamiento, idArtistaAlbum, generoPrincipal);
                    break;

                case 6:
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
                    break;

                case 7:
                    System.out.print("Nombre del Artista: ");
                    String nombreArtista = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento (dd-MM-yyyy): ");
                    String fechaNacimiento = EntradaValidator.pedirFechaNacimiento(scanner);
                    System.out.print("Nacionalidad: ");
                    String nacionalidad = EntradaValidator.pedirNacionalidad(scanner);
                    System.out.print("Géneros: ");
                    String generosArtista = scanner.nextLine();
                    bd.agregarArtista(nombreArtista, fechaNacimiento, nacionalidad, generosArtista);
                    break;

                case 8:
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
                    break;
                case 9: // Editar Álbum
                    // Leer el ID del álbum
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

                    break;

                case 10: // Editar Artista
                    System.out.print("Ingresa el ID del artista (o deja en blanco para usar el nombre): ");
                    String idArtistaInput = scanner.nextLine();
                    Integer idArtistaEdit = idArtistaInput.isEmpty() ? null : EntradaValidator.validationInt(scanner);

                    System.out.print("Ingresa el nombre del artista (deja en blanco si usas el ID): ");
                    String nombreArtistaEdit = scanner.nextLine();

                    System.out.print("Nuevo nombre del artista (deja en blanco para no cambiar): ");
                    String nuevoNombreArtista = scanner.nextLine();

                    System.out.print("Nueva fecha de nacimiento del artista (YYYY-MM-DD, deja en blanco para no cambiar): ");
                    String nuevaFechaNacimiento = scanner.nextLine();

                    System.out.print("Nueva nacionalidad del artista (deja en blanco para no cambiar): ");
                    String nuevaNacionalidad = scanner.nextLine();

                    System.out.print("Nuevo género principal del artista (deja en blanco para no cambiar): ");
                    String nuevoGeneroArtista = scanner.nextLine();

                    bd.editarArtista(idArtistaEdit, nombreArtistaEdit, nuevoNombreArtista, nuevaFechaNacimiento, nuevaNacionalidad, nuevoGeneroArtista);
                    break;

                case 11:
                    System.out.print("Nombre del Género: ");
                    String nombreGenero = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    bd.agregarGenero(nombreGenero, descripcion);
                    break;

                case 12:
                    System.out.println("Géneros:");
                    bd.consultarGeneros().forEach(genero -> System.out.println("Artista encontrado: " + genero.getNombreGenero() +
                                            " -Descripcion: " + genero.getDescripcion()));
                    break;
                case 13:
                    System.out.println("¿Qué deseas listar?");
                    System.out.println("1. Canciones");
                    System.out.println("2. Álbumes");
                    System.out.println("3. Artistas");
                    System.out.print("Selecciona una opción: ");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch(subOpcion) {
                        case 1: // Listar Canciones
                            System.out.print("Filtro por título de la canción (deja en blanco para no filtrar): ");
                            String filtroTituloCancion = scanner.nextLine();
                            System.out.print("Filtro por género de la canción (deja en blanco para no filtrar): ");
                            String filtroGeneroCancion = scanner.nextLine();
                            System.out.print("Filtro por duración de la canción (deja en blanco para no filtrar): ");
                            String filtroDuracionInput = scanner.nextLine();
                            Integer filtroDuracion = filtroDuracionInput.isEmpty() ? null : Integer.parseInt(filtroDuracionInput);
                            System.out.print("¿Ordenar por título? (true/false): ");
                            boolean ordenarPorTituloCancion = scanner.nextBoolean();
                            scanner.nextLine(); // Limpiar el buffer

                            bd.listarCanciones(filtroTituloCancion, filtroGeneroCancion, filtroDuracion, ordenarPorTituloCancion)
                                    .forEach(cancion -> System.out.println("Canción encontrada: " + cancion.getTitulo() +
                                                " Duracion:" + cancion.getDuracion() + " segundos" +
                                                " Artista: " + bd.obtenerArtistaPorId(cancion.getIdArtista()).getNombre() +
                                                " Album: " + bd.obtenerAlbumPorId(cancion.getIdAlbum()).getTitulo() +
                                                " Genero: " + bd.obtenerGeneroPorId(cancion.getIdGenero()).getNombreGenero()));
                            break;

                        case 2: // Listar Álbumes
                            System.out.print("Filtro por título del álbum (deja en blanco para no filtrar): ");
                            String filtroTituloAlbum = scanner.nextLine();
                            System.out.print("Filtro por género principal del álbum (deja en blanco para no filtrar): ");
                            String filtroGeneroAlbum = scanner.nextLine();
                            System.out.print("Filtro por año de lanzamiento del álbum (deja en blanco para no filtrar): ");
                            String filtroAnioLanzamientoInput = scanner.nextLine();
                            Integer filtroAnioLanzamiento = filtroAnioLanzamientoInput.isEmpty() ? null : Integer.parseInt(filtroAnioLanzamientoInput);
                            System.out.print("Filtro por nombre del Artista del álbum (deja en blanco para no filtrar): ");
                            String filtroArtistaNombreAlbum = scanner.nextLine();
                            System.out.print("¿Ordenar por título? (true/false): ");
                            boolean ordenarPorTituloAlbum = scanner.nextBoolean();
                            scanner.nextLine(); // Limpiar el buffer

                            bd.listarAlbumes(filtroTituloAlbum, filtroGeneroAlbum, filtroAnioLanzamiento,filtroArtistaNombreAlbum, ordenarPorTituloAlbum)
                                    .forEach(album -> System.out.println("Álbum encontrado: " + album.getTitulo() +
                                " - Artista: " + bd.obtenerArtistaPorId(album.getIdArtista()).getNombre() +
                                " - Año: " + album.getAnioLanzamiento() +
                                " - Género: " + album.getGeneroPrincipal()));
                            break;

                        case 3: // Listar Artistas
                            System.out.print("Filtro por nombre del artista (deja en blanco para no filtrar): ");
                            String filtroNombreArtista = scanner.nextLine();
                            System.out.print("Filtro por nacionalidad del artista (deja en blanco para no filtrar): ");
                            String filtroNacionalidadArtista = scanner.nextLine();
                            System.out.print("Filtro por género del artista (deja en blanco para no filtrar): ");
                            String filtroGeneroArtista = scanner.nextLine();
                            System.out.print("¿Ordenar por nombre? (true/false): ");
                            boolean ordenarPorNombreArtista = scanner.nextBoolean();
                            scanner.nextLine(); // Limpiar el buffer

                            bd.listarArtistas(filtroNombreArtista, filtroNacionalidadArtista, filtroGeneroArtista, ordenarPorNombreArtista)
                                    .forEach(artista -> System.out.println("Artista encontrado: " + artista.getNombre() +
                                " - Fecha Nacimiento: " + artista.getFechaNacimiento() +
                                " - Nacionalidad: " + artista.getNacionalidad() +
                                " - Género: " + artista.getGeneros()));
                            break;

                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;

                case 14:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 14);

        scanner.close();
    }
}