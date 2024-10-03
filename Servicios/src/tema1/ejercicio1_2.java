/*
 * Realiza un programa java que admita argumentos desde main() y devuelva con System.exit() los siguientes valores:
    Si el número de argumentos es <1 debe devolver 1.
	Si el argumento es una cadena debe devolver 2.
	si el argumento es un número entero menor que o debe devolver 3.
    En cualquier otra situación debe devolver 0.
	Realiza un segundo programa java que ejecute al anterior. Este segundo programa deberá mostrar en pantalla lo que pasa 
	dependiendo del valor devuelto al ejecutar el programa anterior
 */


package tema1;

public class ejercicio1_2 {

    public static void main(String[] args) {
        // Si el número de argumentos es menor a 1, devolver 1
        if (args.length < 1) {
            System.out.println("Error: No se proporcionaron argumentos.");
            System.exit(1);
        }

        String argumento = args[0];
        
        // Intentar parsear el argumento a un número entero
        try {
            int numero = Integer.parseInt(argumento);
            
            // Si el número es menor que 0, devolver 3
            if (numero < 0) {
                System.out.println("Error: El argumento es un número entero negativo.");
                System.exit(3);
            }
        } catch (NumberFormatException e) {
            // Si el argumento no es un número entero, devolver 2
            System.out.println("Error: El argumento es una cadena.");
            System.exit(2);
        }

        // Si no hay ningún error, devolver 0
        System.out.println("Argumento válido.");
        System.exit(0);
    }
}


