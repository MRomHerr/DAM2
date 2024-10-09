package Tema1Ficheros.Tema1Empleado;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class BuscarEmpleado {
    public static void main(String[] args) throws IOException {
        File fichero = new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1Empleado\\AleatorioEmple.dat"); // Declara el fichero de acceso aleatorio.
        RandomAccessFile file = new RandomAccessFile(fichero, "r"); // r solo lee el fichero, w solo escribe el fichero rw ambas
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Introduce el id del empleado: ");
        int idBuscado = sc.nextInt();
        
        int id, dep, posicion;
        double salario;
        char apellido[] = new char[10], aux;
        
        boolean encontrado = false;
        posicion = 0; // Para situarnos al principio

        // Recorro el fichero
        while (file.getFilePointer() < file.length()) {
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

            if (id == idBuscado) {
                System.out.printf("ID: %d | Apellido: %s | Departamento: %d | Salario: %.2f%n",
                        id, apellidos.trim(), dep, salario);
                encontrado = true;
                break;
            }

            // Me posiciono para el siguiente empleado, cada empleado ocupa 36 bytes
            posicion += 36;
        }

        if (!encontrado) {
            System.out.println("Empleado no encontrado.");
        }

        file.close(); // Cerrar fichero
        sc.close(); // Cerrar scanner
    }
}
