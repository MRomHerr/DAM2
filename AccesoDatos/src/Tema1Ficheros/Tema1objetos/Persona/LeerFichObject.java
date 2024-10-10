package Tema1Ficheros.Tema1objetos.Persona;

import java.io.*;
import java.util.Scanner;

public class LeerFichObject {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner sc = new Scanner(System.in);
		File fichero = new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Persona\\FichPersona.dat");

		// opcion para leer personas
		System.out.println("¿Desea leer las personas del archivo? (si/no)");
		String respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("si")) {
			leerPersonas(fichero);
		}

		// opcion para añadir una nueva persona
		System.out.println("¿Desea añadir una nueva persona? (si/no)");
		respuesta = sc.nextLine();

		if (respuesta.equalsIgnoreCase("si")) {
			añadirPersona(fichero, sc);
		}

		sc.close(); // cerrar el escaner
	}

	// metodo para leer personas del archivo
	private static void leerPersonas(File fichero) {
		Persona persona; // defino la variable persona

		// crea el flujo de entrada
		try (FileInputStream filein = new FileInputStream(fichero);
			 ObjectInputStream dataIS = new ObjectInputStream(filein)) {
			while (true) { // lectura del fichero
				persona = (Persona) dataIS.readObject(); // leer una persona
				System.out.printf("Nombre: %s, Edad: %d%n", persona.getNombre(), persona.getEdad());
			}
		} catch (EOFException eo) {
			System.out.println("fin de lectura.");
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("error al leer del archivo: " + e.getMessage());
		}
	}

	// metodo para añadir una nueva persona al archivo
	private static void añadirPersona(File fichero, Scanner sc) {
		Persona nuevaPersona = new Persona();
		System.out.print("Introduzca el nombre de la persona: ");
		nuevaPersona.setNombre(sc.nextLine());
		System.out.print("Introduzca la edad de la persona: ");
		nuevaPersona.setEdad(sc.nextInt());

		// guardar la nueva persona en el archivo
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero, true))) {
			oos.writeObject(nuevaPersona);
			System.out.println("persona añadida correctamente.");
		} catch (IOException e) {
			System.out.println("error al escribir en el archivo: " + e.getMessage());
		}
	}
}
