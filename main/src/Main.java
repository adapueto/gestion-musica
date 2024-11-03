import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Basededatos bd = new Basededatos();
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
            System.out.println("9. Agregar Género");
            System.out.println("10. Consultar Géneros");
            System.out.println("11. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Duración (en minutos): ");
                    int duracion = scanner.nextInt();
                    System.out.print("ID del Álbum: ");
                    int idAlbum = scanner.nextInt();
                    System.out.print("ID del Artista: ");
                    int idArtista = scanner.nextInt();
                    System.out.print("ID del Género: ");
                    int idGenero = scanner.nextInt();
                    bd.agregarCancion(titulo, duracion, idAlbum, idArtista, idGenero);
                    System.out.println("Cancion Agregada. Lista:");
                    break;

                case 2:
                    System.out.print("Titulo: ");
                    String consultarTitulo = scanner.nextLine();
                    consultarTitulo = consultarTitulo.isEmpty() ? null : consultarTitulo;

                    System.out.print("Artista: ");
                    String consultarArtista = scanner.nextLine();
                    consultarArtista = consultarArtista.isEmpty() ? null : consultarArtista;

                    // System.out.print("Album: ");
                    // String consultarAlbum = scanner.nextLine();
                    // consultarAlbum = consultarAlbum.isEmpty() ? null : consultarAlbum;

                    // System.out.print("Genero: ");
                    // String consultarGenero = scanner.nextLine();
                    // consultarGenero = consultarGenero.isEmpty() ? null : consultarGenero;
                
                   // System.out.println("Nacionalidad del artista: ");
                   // String consultarNacionA = scanner.next();
                    //System.out.println("Anio de lanzamiento del album: ");
                   // int consultarAnioLanzamiento = scanner.nextInt();
                    bd.consultaCanciones(consultarTitulo, consultarArtista).forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("ID de la Canción a editar: ");
                    int idEditar = scanner.nextInt();
                    System.out.print("Nuevo Título: ");
                    scanner.nextLine(); 
                    String nuevoTitulo = scanner.nextLine();
                    System.out.print("Nueva Duración: ");
                    int nuevaDuracion = scanner.nextInt();
                    System.out.print("Nuevo ID de Álbum: ");
                    int nuevoIdAlbum = scanner.nextInt();
                    System.out.print("Nuevo ID de Artista: ");
                    int nuevoIdArtista = scanner.nextInt();
                    System.out.print("Nuevo ID de Género: ");
                    int nuevoIdGenero = scanner.nextInt();
                    bd.editarCancion(idEditar, nuevoTitulo, nuevaDuracion, nuevoIdAlbum, nuevoIdArtista, nuevoIdGenero);
                    break;

                case 4:
                    System.out.print("ID de la Canción a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    bd.eliminarCancion(idEliminar);
                    break;

                case 5:
                    System.out.print("Título del Álbum: ");
                    String tituloAlbum = scanner.nextLine();
                    System.out.print("Año de Lanzamiento: ");
                    int anioLanzamiento = scanner.nextInt();
                    System.out.print("ID del Artista: ");
                    int idArtistaAlbum = scanner.nextInt();
                    System.out.print("Género Principal: ");
                    String generoPrincipal = scanner.next();
                    bd.agregarAlbum(tituloAlbum, anioLanzamiento, idArtistaAlbum, generoPrincipal);
                    break;

                case 6:
                    System.out.println("Álbumes:");
                    bd.consultarAlbums().forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("Nombre del Artista: ");
                    String nombreArtista = scanner.nextLine();
                    System.out.print("Fecha de Nacimiento: ");
                    String fechaNacimiento = scanner.nextLine();
                    System.out.print("Nacionalidad: ");
                    String nacionalidad = scanner.nextLine();
                    System.out.print("Géneros: ");
                    String generosArtista = scanner.nextLine();
                    bd.agregarArtista(nombreArtista, fechaNacimiento, nacionalidad, generosArtista);
                    break;

                case 8:
                    System.out.println("Artistas:");
                    bd.consultarArtistas().forEach(System.out::println);
                    break;

                case 9:
                    System.out.print("Nombre del Género: ");
                    String nombreGenero = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String descripcion = scanner.nextLine();
                    bd.agregarGenero(nombreGenero, descripcion);
                    break;

                case 10:
                    System.out.println("Géneros:");
                    bd.consultarGeneros().forEach(System.out::println);
                    break;

                case 11:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 11);

        scanner.close();
    }
}