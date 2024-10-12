package tema1.Suma1_5;

import java.util.Scanner;

public class Suma1_5  {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1;
        int num2;

        System.out.println("Introduzca un numero");
        num1=sc.nextInt();
        System.out.println("Introduzca otro numero");
        num2=sc.nextInt();

        int suma = num1+num2;

        System.out.println(suma);
        sc.close();
    }
}