package tema1.CadenaAsterisco1_6;

import java.io.File;
import java.io.InputStream;

public class EjecutarCadenas1_6 {

    public static void main(String[] args) {
        try {
            File directorio = new File(".\\bin");

            ProcessBuilder pb = new ProcessBuilder("java","Cadenas1_6");
            pb.directory(directorio);

            Process p = pb.start();

            InputStream is = p.getInputStream();
            int c;
            while((c=is.read())!= -1) {
                System.out.print((char)c);
            }
            is.close();

            int exitVal = p.waitFor();
            System.out.println(exitVal);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}