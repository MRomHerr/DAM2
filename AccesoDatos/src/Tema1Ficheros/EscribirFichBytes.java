package Tema1Ficheros;

import java.io.*;

public class EscribirFichBytes {
    public static void main(String[] args) throws IOException {
        
        File fichero = new File(".\\src\\Tema1\\FichData.dat");
        FileOutputStream fileout = new FileOutputStream(fichero); 
        DataOutputStream dataOS = new DataOutputStream(fileout);
        
        String nombres[] = {"Ana", "Luis Miguel", "Alicia", "Pedro", "Manuel", "Andrés", "Julio", "Antonio", "María Jesús"};
        int edades[] = {14, 15, 13, 15, 16, 12, 16, 14, 13};
        double alturas[] = {1.43, 1.80, 1.70, 1.75, 1.64, 1.68, 1.66, 1.54, 1.43};
        boolean asistencias[] = {true, true, false, true, false, false, true, false, false};
        
        //escribir los arrays
        for (int i = 0; i < edades.length; i++) {
            dataOS.writeUTF(nombres[i]); // escribe nombre 
            dataOS.writeInt(edades[i]); // escribe edad
            dataOS.writeDouble(alturas[i]); // escribe altura
            dataOS.writeBoolean(asistencias[i]); // escribe asistencia
        }
        dataOS.close(); // cerrar stream
        
        //leer los datos del archivo
        FileInputStream filein = new FileInputStream(fichero);
        DataInputStream dataIS = new DataInputStream(filein);
        
        //asistentes
        try {
        	System.out.println("Asistentes:");
            while (true) {
                String nombre = dataIS.readUTF(); // lee nombre 
                int edad = dataIS.readInt(); // lee edad
                double altura = dataIS.readDouble(); // lee altura
                boolean asistencia = dataIS.readBoolean(); // lee asistencia
                
                if(asistencia==true) {
                	
                System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Altura: " + altura + ", Asistencia: " + asistencia);
                }else {
                	System.out.print("");
                }
            }
            
        } catch (EOFException eo) {

        }
        
        
        //no asisten
        try {
        	//leer los datos del archivo
            filein = new FileInputStream(fichero);
            dataIS = new DataInputStream(filein);
            
            System.out.println("");
        	System.out.println("No asisten:");
            while (true) {
                String nombre = dataIS.readUTF(); // lee nombre 
                int edad = dataIS.readInt(); // lee edad
                double altura = dataIS.readDouble(); // lee altura
                boolean asistencia = dataIS.readBoolean(); // lee asistencia
                
                if(asistencia==false) {
                	
                System.out.println("Nombre: " + nombre + ", Edad: " + edad + ", Altura: " + altura + ", Asistencia: " + asistencia);
                }else {
                	System.out.print("");
                }
            }
            
        } catch (EOFException eo) {
            // Fin del archivo
        }
        dataIS.close(); // cerrar stream
    }
}
