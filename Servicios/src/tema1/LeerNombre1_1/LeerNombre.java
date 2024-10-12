package tema1.LeerNombre1_1;

/*
El programa recibe un argumento (un nombre) desde la línea de comandos, y debe finalizar con un código
de salida específico dependiendo de si se ha introducido el argumento correctamente o no.
 */

public class LeerNombre {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No se ha proporcionado ningún nombre.");
            System.exit(-1);
        }

        String nombre = args[0];
        System.out.println(nombre);
        System.exit(0);

    }

}