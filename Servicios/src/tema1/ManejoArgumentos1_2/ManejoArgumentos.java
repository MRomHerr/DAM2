package tema1.ManejoArgumentos1_2;

/*
El primer programa debe verificar el número y tipo de argumentos recibidos y devolver diferentes códigos
de salida según las condiciones especificadas. Lo llamaremos ManejoArgumentos.java.
 */

public class ManejoArgumentos {
    public static void main(String[] args) {
        // Si no hay argumentos, devolver 1
        if (args.length < 1) {
            System.exit(1);
        }

        // Si el argumento es una cadena que no se puede convertir a número, devolver 2
        try {
            int numero = Integer.parseInt(args[0]);

            // Si el argumento es un número entero menor que 0, devolver 3
            if (numero < 0) {
                System.exit(3);
            }
        } catch (NumberFormatException e) {
            // Si no es un número (es una cadena), devolver 2
            System.exit(2);
        }

        // En cualquier otra situación, devolver 0
        System.exit(0);
    }
}
