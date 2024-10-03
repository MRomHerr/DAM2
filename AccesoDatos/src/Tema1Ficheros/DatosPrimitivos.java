package Tema1Ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DatosPrimitivos {

    public static void main(String[] args) throws IOException {
        
        // Create the file
        File f2 = new File("C:\\Users\\aludam2\\Downloads\\primitivos.dat");
        f2.createNewFile();
        
        // Create output stream to write data
        FileOutputStream fileout = new FileOutputStream(f2);
        DataOutputStream dataOS = new DataOutputStream(fileout);
        
        // Primitive data types
        int v = 1;
        char c = 5;
        Boolean b = true;
        String n = "hola";
        byte by = 0;
        
        // Writing data in the same order
        dataOS.writeByte(by);
        dataOS.writeChar(c);
        dataOS.writeBoolean(b);
        dataOS.writeInt(v);
        dataOS.writeUTF(n);      
        
        dataOS.close(); // Close the output stream
        
        // Create input stream to read data
        FileInputStream filein = new FileInputStream(f2);
        DataInputStream dataIS = new DataInputStream(filein);
        
        // Read and print data in the same order they were written
        try {
            while (true) { // Use an infinite loop and catch EOFException to stop
                by = dataIS.readByte();
                System.out.println("Esto es un Byte: " + by);
                
                c = dataIS.readChar();
                System.out.println("Esto es un Caracter: " + (int)c);
                
                b = dataIS.readBoolean();
                System.out.println("Esto es un Boolean: " + b);
                
                v = dataIS.readInt();
                System.out.println("Esto es un entero: " + v);
                
                n = dataIS.readUTF();
                System.out.println("Esto es un String: " + n);
            }
        } catch (EOFException eo) {
            // End of file reached
            System.out.println("Fin del archivo");
        }
        
        dataIS.close(); // Close the input stream
    }
}
