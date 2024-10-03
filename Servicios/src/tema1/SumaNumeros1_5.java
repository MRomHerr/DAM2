package tema1;
/*
 * escribe un programa en java que lea 2 numeros desde la entrada estandar y 
 * visualice su uma. ontrola que lo introducid por teclado sean dos numeros. 
 * Luego haz otro programa que eejecute el anterior
 */

import java.util.Scanner;

public class SumaNumeros1_5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Variables para almacenar los números
        double num1 = 0, num2 = 0;
        boolean validInput = false;
        
        // Leer primer número, controlando que sea un número válido
        while (!validInput) {
            System.out.print("Introduce el primer número: ");
            if (scanner.hasNextDouble()) {
                num1 = scanner.nextDouble();
                validInput = true;
            } else {
                System.out.println("Error: Por favor, introduce un número válido.");
                scanner.next(); // Descartar la entrada inválida
            }
        }
        
        validInput = false;
        
        // Leer segundo número, controlando que sea un número válido
        while (!validInput) {
            System.out.print("Introduce el segundo número: ");
            if (scanner.hasNextDouble()) {
                num2 = scanner.nextDouble();
                validInput = true;
            } else {
                System.out.println("Error: Por favor, introduce un número válido.");
                scanner.next(); // Descartar la entrada inválida
            }
        }
        
        // Calcular la suma
        double suma = num1 + num2;
        
        // Mostrar el resultado
        System.out.println("La suma de " + num1 + " y " + num2 + " es: " + suma);
    }
}
