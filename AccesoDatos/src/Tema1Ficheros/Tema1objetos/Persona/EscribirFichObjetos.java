package Tema1Ficheros.Tema1objetos.Persona;

import java.io.*;

public class EscribirFichObjetos {
	public static void main(String[] args) throws IOException {
		Persona persona;//defino variable persona
		//declara el fichero
		File fichero = new File("C:\\Users\\aludam2\\IdeaProjects\\DAM2\\AccesoDatos\\src\\Tema1Ficheros\\Tema1objetos\\Persona\\FichPersona.dat");
		//crea el flujo de salida
		FileOutputStream fileout = new FileOutputStream(fichero);
		//conecta el flujo de bytes al flujo de datos
		ObjectOutputStream dataOS = new ObjectOutputStream(fileout);
		
		String nombres[] = {"Ana","Luis Miguel","Alicia","Pedro", "Manuel","Andres","Julio","Antonio","Maria Jesus"};
		
		int edades[] = {14,15,13,15,16,12,16,14,13};
		
		for (int i=0;i<edades.length;i++) { //recorro los arrays
			persona= new Persona(nombres[i],edades[i]);
			dataOS.writeObject(persona);//escribo la persona en el fichero
		}
		dataOS.close(); //craer stream de salida
		}
	}

