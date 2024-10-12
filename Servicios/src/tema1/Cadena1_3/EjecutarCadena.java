package tema1.Cadena1_3;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class EjecutarCadena {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String cadena;
            System.out.println("Introduzca una cadena");
            cadena = sc.nextLine();

            File directorio = new File("C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\Cadena1_3\\Cadena.java");

            ProcessBuilder pb = new ProcessBuilder("java","Cadena.java",cadena);
            pb.directory(directorio);

            Process p = pb.start();

            InputStream is = p.getInputStream();
            int c;
            while((c=is.read())!= -1) {
                System.out.print((char)c);
            }
            is.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}