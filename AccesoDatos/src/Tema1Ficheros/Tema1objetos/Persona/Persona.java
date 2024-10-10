package Tema1Ficheros.Tema1objetos.Persona;

import java.io.Serializable;

public class Persona implements Serializable {
	private static final long serialVersionUID = 1L; // Identificador de versión de la clase

	private String nombre;
	private int edad;

	// Constructor con parámetros
	public Persona(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	// Constructor vacío
	public Persona() {
		this.nombre = null;
		this.edad = 0; // Es una buena práctica inicializar todos los atributos
	}

	// Métodos setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	// Métodos getters
	public String getNombre() {
		return this.nombre; // Devuelve el nombre
	}

	public int getEdad() {
		return this.edad; // Devuelve la edad
	}
} // fin de la clase Persona
