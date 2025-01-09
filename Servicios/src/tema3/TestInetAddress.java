package tema3;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestInetAddress {
    public static void main(String[] args) {
        InetAddress dir = null;
        System.out.println("================================");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
            // Obtiene la dirección IP de localhost
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir); // Llama a un método (no mostrado) para probar varios métodos de InetAddress

            System.out.println("================================");
            System.out.println("SALIDA PARA UNA URL:");
            // Obtiene la dirección IP de www.google.es
            dir = InetAddress.getByName("www.google.es");
            pruebaMetodos(dir);

            // Obtiene todas las direcciones IP asociadas con el nombre de host actual
            System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());

            // Imprime todas las direcciones IP encontradas
            for (int i = 0; i < direcciones.length; i++)
                System.out.println("\t\t" + direcciones[i].toString());

            System.out.println("=========");
        } catch (UnknownHostException e) {
            // Maneja la excepción si no se puede resolver el nombre de host
            e.printStackTrace();
        }
    }//main


    private static void pruebaMetodos(InetAddress dir) {
        // Muestra el resultado del método getByName()
        System.out.println("\tMetodo getByName(): " + dir);

        InetAddress dir2;
        try {
            // Obtiene y muestra la dirección IP local
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS MÉTODOS DE LA CLASE InetAddress
        // Obtiene y muestra el nombre del host
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());

        // Obtiene y muestra la dirección IP como una cadena
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());

        // Muestra la representación en cadena del objeto InetAddress
        System.out.println("\tMetodo toString(): " + dir.toString());

        // Obtiene y muestra el nombre canónico del host
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }//pruebaMetodos

}
