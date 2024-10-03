package Tema1Ficheros;

import java.io.File;
import java.util.Scanner;

public class PrimerEjercicio {
	public static void main (String args[]) {
		Scanner sc=new Scanner (System.in);
		
		String dir="."; //directorio actual
		File f = new File (dir);
		String[] archivos = f.list();
		System.out.printf("Ficheros en el directorio actual: %d %n",archivos.length);
		
		for(int i=0; i<archivos.length;i++) {
			File f2 = new File(f, archivos[i]);
			System.out.printf("Nombre: %s, es fichero?: %b, es directorio?: %b %n",archivos[i], f2.isFile(),f2.isDirectory());
		}

	}
}



