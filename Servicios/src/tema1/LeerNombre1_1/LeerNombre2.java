package tema1.LeerNombre1_1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/*
Este segundo programa debe ejecutar LeerNombre.java como un proceso externo, y utilizar el
metodo waitFor() para capturar el valor de salida del proceso. Dependiendo de si se introducen
los argumentos correctamente o no, el proceso devolverá 1 o -1.
 */


public class LeerNombre2{
    public static void main(String[] args) throws InterruptedException {
            try {
                File directorio = new File("C:\\Users\\marco\\IdeaProjects\\DAM2\\Servicios\\src\\tema1\\LeerNombre1_1\\LeerNombre.java");

                ProcessBuilder pb = new ProcessBuilder("java","LeerNombre.java");
                pb.directory(directorio);

                Process p = pb.start();

                int exitVal = p.waitFor();

                System.out.println("Valor de salida: " + exitVal);

                InputStream is = p.getInputStream();
                int c;
                while((c=is.read())!= -1) {
                    System.out.print((char)c);
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

    }