package tema1;

import java.util.Scanner;

public class LeerCadenas1_6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        
        // Leer cadenas hasta que se introduzca un asterisco (*)
        while (true) {
            System.out.print("Introduce una cadena (o '*' para salir): ");
            input = scanner.nextLine();
            
            // Comprobar si se introdujo un asterisco
            if (input.equals("*")) {
                System.out.println("Has introducido un asterisco. Finalizando el programa.");
                break;
            }
            
            // Mostrar la cadena ingresada
            System.out.println("Has introducido: " + input);
        }
    }
}
