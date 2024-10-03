package tema1;

public class VisualizarCadena {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No se ha proporcionado ninguna cadena.");
            System.exit(1);
        } else {
            String cadena = args[0];
            for (int i = 0; i < 5; i++) {
                System.out.println(cadena);
            }
        }
    }
}
