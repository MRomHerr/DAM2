package Tema1Ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//crear un programa que añade a el fichero de los datos primitivos un long
public class DatosPrimitivos2 {

    public static void main(String[] args) throws IOException {
        
        // Ruta del fichero previamente creado
        File f2 = new File("C:\\Users\\aludam2\\Downloads\\primitivos.dat");
        
        // Primero leer los datos existentes
        System.out.println("Leyendo datos originales del archivo:");
        FileInputStream filein = new FileInputStream(f2);
        DataInputStream dataIS = new DataInputStream(filein);

        try {
            // Leer el archivo en el orden original (byte, char, boolean, int, string)
            byte by = dataIS.readByte();
            System.out.println("Esto es un Byte: " + by);

            char c = dataIS.readChar();
            System.out.println("Esto es un Caracter: " + (int)c);

            boolean b = dataIS.readBoolean();
            System.out.println("Esto es un Boolean: " + b);

            int v = dataIS.readInt();
            System.out.println("Esto es un entero: " + v);

            String n = dataIS.readUTF();
            System.out.println("Esto es un String: " + n);
        } catch (EOFException eo) {
            System.out.println("Fin del archivo original.");
        } finally {
            dataIS.close(); // Cerrar el input stream después de leer
        }

        // Ahora vamos a agregar un valor long al archivo
        long valorLong = 123456789L; // Valor long a agregar

        // Abre el archivo en modo de escritura para agregar datos
        FileOutputStream fileout = new FileOutputStream(f2, true); // 'true' para append
        DataOutputStream dataOS = new DataOutputStream(fileout);

        System.out.println("\nAñadiendo el valor long al archivo: " + valorLong);
        dataOS.writeLong(valorLong); // Escribir el valor de tipo long
        dataOS.close(); // Cerrar el output stream

        // Leer nuevamente el archivo, incluyendo el nuevo valor long
        System.out.println("\nLeyendo datos después de añadir el long:");
        filein = new FileInputStream(f2); // Re-abrir el archivo para leer
        dataIS = new DataInputStream(filein);

        try {
            // Leer el archivo en el orden original
            byte by = dataIS.readByte();
            System.out.println("Esto es un Byte: " + by);

            char c = dataIS.readChar();
            System.out.println("Esto es un Caracter: " + (int)c);

            boolean b = dataIS.readBoolean();
            System.out.println("Esto es un Boolean: " + b);

            int v = dataIS.readInt();
            System.out.println("Esto es un entero: " + v);

            String n = dataIS.readUTF();
            System.out.println("Esto es un String: " + n);

            // Leer el nuevo valor de tipo long
            long longValue = dataIS.readLong();
            System.out.println("Esto es un Long: " + longValue);

        } catch (EOFException eo) {
            System.out.println("Fin del archivo.");
        } finally {
            dataIS.close(); // Cerrar el input stream después de leer
        }
    }
}


