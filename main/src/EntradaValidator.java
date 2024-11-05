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
            }
        }

        return validationNumber;
    }

    
}
