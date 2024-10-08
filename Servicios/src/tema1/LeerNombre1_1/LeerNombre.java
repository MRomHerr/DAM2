package tema1.LeerNombre1_1;

/*
El programa recibe un argumento (un nombre) desde la línea de comandos, y debe finalizar con un código
de salida específico dependiendo de si se ha introducido el argumento correctamente o no.
 */

public class LeerNombre {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Debe ingresar un nombre como argumento.");
            System.exit(-1); // Finalización incorrecta
        }

        // Imprimir el nombre
        System.out.println("El nombre introducido es: " + args[0]);
        System.exit(1); // Finalización correcta
    }
}
