package tema1.CadenaAsterisco1_6;

import java.util.Scanner;

public class Cadenas1_6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena = "";

        while(!cadena.equals("*")) {
            System.out.println("Introduzca una cadena");
            cadena=sc.nextLine();
        }
        sc.close();
    }

}