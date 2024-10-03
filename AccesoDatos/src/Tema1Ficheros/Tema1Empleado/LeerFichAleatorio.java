package Tema1Ficheros.Tema1Empleado;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerFichAleatorio {
    public static void main(String[] args) throws IOException{
        File fichero = new File(".\\src\\Tema1Empleado\\AleatorioEmple.dat"); // Declara el fichero de acceso aleatorio.
        RandomAccessFile file = new RandomAccessFile(fichero, "r");
            int id, dep, posicion;
            double salario;
            char apellido[] = new char[10], aux;
            
            posicion = 0; // Para situarnos al principio

            // Recorro el fichero
            for(;;) {
                file.seek(posicion); // Nos posicionamos en la posición actual
                id = file.readInt(); // Obtengo id de empleado

                // Recorro uno a uno los caracteres del apellido
                for (int i = 0; i < apellido.length; i++) {
                    aux = file.readChar(); // Leo un carácter
                    apellido[i] = aux; // Los voy guardando en el array
                }

                // Convierto a String el array
                String apellidos = new String(apellido);

                dep = file.readInt(); // Obtengo departamento
                salario = file.readDouble(); // Obtengo salario

                if (id > 0) {
                    System.out.printf("ID: %d | Apellido: %s | Departamento: %d | Salario: %.2f%n",
                            id, apellidos.trim(), dep, salario);
                }

                // Me posiciono para el siguiente empleado, cada empleado ocupa 36 bytes
                posicion += 36;

                // Si he recorrido todos los bytes, salgo del bucle
                if (file.getFilePointer() == file.length())break;
            }//fin bucle for
            file.close();//cerrar fichero
    }
}

