import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos();
        GestorMusica gestor = new GestorMusica(bd);
        bd.guardarDatosComoJson("datos_guardados_inicial.json");
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            // System.out.println("Sistema de Gestión de Música");
            // System.out.println("1. Agregar Canción");
            // System.out.println("2. Consultar Canciones");
            // System.out.println("3. Editar Canción");
            // System.out.println("4. Eliminar Canción");
            // System.out.println("5. Agregar Álbum");
            // System.out.println("6. Consultar Álbumes");
            // System.out.println("7. Agregar Artista");
            // System.out.println("8. Consultar Artistas");
            // System.out.println("9. Editar Album");
            // System.out.println("10. Editar Artista");
            // System.out.println("11. Agregar Género");
            // System.out.println("12. Consultar Géneros");
            // System.out.println("13. Listas de todo");
            // System.out.println("14. Salir");
            // System.out.print("Selecciona una opción: ");
            // opcion = scanner.nextInt();
            // scanner.nextLine(); // Limpiar el buffer
            System.out.println("-----------------------------");
            System.out.println("Sistema de Gestión de Música");
            System.out.println("-----------------------------");
            System.out.println("1. Agregar");
            System.out.println("2. Consultar");
            System.out.println("3. Modificar");
            System.out.println("4. Eliminar");
            System.out.println("5. Listar Todo");
            System.out.println("6. Guardar Base de Datos formato json");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer


            switch (opcion) {
                case 1 -> agregarEntidad(gestor, scanner);
                case 2 -> ConsultarEntidad(gestor, scanner);
                case 3 -> modificarEntidad(gestor, scanner);
                case 4 -> eliminarEntidad(gestor, scanner);
                case 5 -> {
                    System.out.println("¿Qué deseas listar?");
                    System.out.println("------------------");
                    System.out.println("1. Canciones");
                    System.out.println("2. Álbumes");
                    System.out.println("3. Artistas");
                    System.out.print("Selecciona una opción: ");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch(subOpcion) {
                        case 1 -> {
                            // Listar Canciones
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
                    }

                        case 2 -> {
                            // Listar Álbumes
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
                    }

                        case 3 -> {
                            // Listar Artistas
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
                    }

                        default -> System.out.println("Opción no válida.");
                    }
                    break;

                }
                case 6 -> {
                    System.out.print("Ingrese el nombre del archivo para guardar los datos (por ejemplo, datos.json): ");
                    String nombreArchivo = scanner.nextLine();
                    bd.guardarDatosComoJson(nombreArchivo);
                    }
                case 7 -> {
                    System.out.println("Saliendo...");
                    break;
                    }
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 7);

        scanner.close();
    }
                    

            
    private static void agregarEntidad(GestorMusica gestor, Scanner scanner) {
        boolean continuar = true;

        while (continuar){
            System.out.println("¿Qué deseas agregar?");
            System.out.println("--------------------");
            System.out.println("1. Canción");
            System.out.println("2. Álbum");
            System.out.println("3. Artista");
            System.out.println("4. Género");
            System.out.println("5. Regresar al Menu");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            switch (tipo) {
                case 1 -> gestor.agregarCancion(scanner);
                case 2 -> gestor.agregarAlbum(scanner);
                case 3 -> gestor.agregarArtista(scanner);
                case 4 -> gestor.agregarGenero(scanner);
                case 5 -> {
                // Salir del bucle y volver al menú principal
                System.out.println("Regresando al menú principal...");
                continuar = false;
            }
            }
        }
    }
    private static void ConsultarEntidad(GestorMusica gestor, Scanner scanner){
        boolean continuar = true;

        while (continuar){
            System.out.println("¿Qué deseas consultar?");
            System.out.println("---------------------");
            System.out.println("1. Canción");
            System.out.println("2. Álbum");
            System.out.println("3. Artista");
            System.out.println("4. Género");
            System.out.println("5. Regresar al Menu");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            switch (tipo) {
                    case 1 -> gestor.consultarCanciones(scanner);
                    case 2 -> gestor.consultarAlbum(scanner);
                    case 3 -> gestor.consultarArtista(scanner);
                    case 4 -> gestor.consultarGenero(scanner);
                    case 5 -> {
                    // Salir del bucle y volver al menú principal
                    System.out.println("Regresando al menú principal...");
                    continuar = false;
                }
            }
        }
    }

    private static void eliminarEntidad(GestorMusica gestor, Scanner scanner){
        
        boolean continuar = true;

        while (continuar){
            System.out.println("¿Qué deseas eliminar?");
            System.out.println("---------------------");
            System.out.println("1. Canción");
            System.out.println("2. Regresar al Menu");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            switch (tipo) {
                    case 1 -> gestor.eliminarCancion(scanner);
                    case 2 -> {
                    // Salir del bucle y volver al menú principal
                    System.out.println("Regresando al menú principal...");
                    continuar = false;
                }
            }
        }
    }

    private static void modificarEntidad(GestorMusica gestor, Scanner scanner){
        boolean continuar = true;

        while (continuar){
            System.out.println("¿Qué deseas modificar?");
            System.out.println("-----------------------");
            System.out.println("1. Canción");
            System.out.println("2. Álbum");
            System.out.println("3. Artista");
            System.out.println("4. Regresar al Menu");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            switch (tipo) {
                    case 1 -> gestor.modificarCancion(scanner);
                    case 2 -> gestor.modificarAlbum(scanner);
                    case 3 -> gestor.modificarArtista(scanner);
                    case 4 -> {
                    // Salir del bucle y volver al menú principal
                    System.out.println("Regresando al menú principal...");
                    continuar = false;
                }
            }
        }
    }
}


                