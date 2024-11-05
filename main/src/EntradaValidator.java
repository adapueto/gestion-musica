import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaValidator {

    public static int validationInt(Scanner scanner) {
        return validationInt(scanner, false, false);
    }

    public static int validationInt(Scanner scanner, boolean validarAnio) {
        return validationInt(scanner, true, false);
    }

    public static Integer validationInt(Scanner scanner, boolean validarAnio, boolean checknull) {
        Integer validationNumber = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            
            try {

                if (checknull) {
                    String input = scanner.nextLine();
                    if (input.isEmpty()) {
                        validationNumber = null;
                        entradaValida = true;
                    } else {
                        validationNumber = Integer.parseInt(input);
                    }
                }else {
                    validationNumber = scanner.nextInt();
                }
                
                
                if (validationNumber != null) {

                
                    if (validarAnio) {
                        if (validationNumber >= 1900 && validationNumber <= 2024) {
                                entradaValida = true;  // El año está dentro del rango válido
                            } else {
                                System.out.println("Por favor, ingrese un año válido entre 1900 y 2024.");
                        }

                    } else {
                        if (validationNumber > 0) {

                            entradaValida = true;  // Salimos del bucle si la duración es válida
                        } else {
                            System.out.println("Por favor, ingrese una variable válida mayor a 0.");
                        }
                    }
                }
                
            } catch (InputMismatchException e) {
                // Si la entrada no es un número entero, capturamos la excepción
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                scanner.nextLine();  // Limpiar el buffer del scanner
            } catch (NumberFormatException e) {
            // Capturamos excepciones cuando Integer.parseInt falla
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                //scanner.nextLine();  // Limpiamos el buffer de scanner
            }
        }

        return validationNumber;
    }

    public static String pedirNacionalidad(Scanner scanner) {
        String nacionalidad = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                
                nacionalidad = scanner.nextLine();

                // Validamos que no haya números en la nacionalidad
                if (nacionalidad.matches(".*\\d.*")) {  // Regex para encontrar números
                    throw new IllegalArgumentException("La nacionalidad no puede contener números. Por favor, ingrese una nacionalidad válida.");
                }

                entradaValida = true;  // Si pasa la validación, salimos del bucle

            } catch (IllegalArgumentException e) {
                // Si hay un error (por ejemplo, si la entrada contiene números)
                System.out.println(e.getMessage());
            }
        }

        return nacionalidad;
    }

    public static String pedirFechaNacimiento(Scanner scanner) {
        String fecha = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                
                fecha = scanner.nextLine();

                // Verificar el formato de la fecha
                if (!fecha.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                    throw new IllegalArgumentException("Formato de fecha incorrecto. Debe ser dd-MM-yyyy.");
                }

                // Dividir la fecha en día, mes y año
                String[] partes = fecha.split("-");
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int año = Integer.parseInt(partes[2]);

                // Validar el año (entre 1900 y 2024)
                if (año < 1900 || año > 2024) {
                    throw new IllegalArgumentException("El año debe estar entre 1900 y 2024.");
                }

                // Validar el mes (entre 1 y 12)
                if (mes < 1 || mes > 12) {
                    throw new IllegalArgumentException("El mes debe estar entre 01 y 12.");
                }

                // Validar el día según el mes
                if (!esDiaValido(dia, mes, año)) {
                    throw new IllegalArgumentException("El día ingresado no es válido para el mes y año proporcionados.");
                }

                // Si todas las validaciones pasan, la entrada es válida
                entradaValida = true;

            } catch (IllegalArgumentException e) {
                // Capturamos el error y mostramos el mensaje
                System.out.println(e.getMessage());
            }
        }

        return fecha;
    }

    public static boolean esDiaValido(int dia, int mes, int año) {
        // Número de días por mes en un año no bisiesto
        int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Si es un año bisiesto, febrero tendrá 29 días
        if (esAnioBisiesto(año)) {
            diasPorMes[1] = 29; // Febrero tiene 29 días en un año bisiesto
        }

        // Comprobar que el día sea válido para el mes
        return dia >= 1 && dia <= diasPorMes[mes - 1];
    }

    // Función que valida si un año es bisiesto
    public static boolean esAnioBisiesto(int año) {
        // Un año es bisiesto si es divisible por 4, pero no por 100, salvo que también sea divisible por 400
        return (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0));
    }

    
}
