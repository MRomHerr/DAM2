package Tema1Ficheros.Tema1objetos;

import java.io.*;

public class LeerFichObject {
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Persona persona; //defino la variable persona
		File fichero = new File("C:\\Users\\aludam2\\Downloads\\FichPersona.dat");
		//crea el flujo de entrad
		FileInputStream filein = new FileInputStream(fichero);
		//conecta el flujo de bytes al flujo de datos
		ObjectInputStream dataIS = new ObjectInputStream(filein);
		
		try {
			while (true) { //lectura del fichero
				persona= (Persona) dataIS.readObject(); //leer una Persona
				System.out.printf("Nombre: %s, edad: %d %n persona.getNombre(),persona.getEdad()");
			}
			} catch (EOFException eo) {
				System.out.println("FIN DE LECTURA.");
			}
			
			dataIS.close();//cerrar Stream entrada
		}
	}
//hay que añadir a una persona
