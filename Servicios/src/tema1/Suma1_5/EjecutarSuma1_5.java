package tema1.Suma1_5;

import java.io.File;
import java.io.InputStream;

public class EjecutarSuma1_5 {

    public static void main(String[] args) {
        try {
            File directorio = new File("C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\Suma1_5\\Suma1_5.java");

            ProcessBuilder pb = new ProcessBuilder("java","Suma1_5.java");
            pb.directory(directorio);

            Process p = pb.start();

            InputStream is = p.getInputStream();
            int c;
            while((c=is.read())!= -1) {
                System.out.print((char)c);
            }

            int exitCode = p.waitFor();
            System.out.println("El proceso ha finalizado con código de salida: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}