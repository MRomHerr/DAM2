package tema1.Cadena1_3;

public class Cadena {

    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("No se ha introducido ninguna cadena");
            System.exit(1);
        }

        String cadena = args[0];

        for(int i=0;i<5;i++) {
            System.out.println(cadena);
        }
    }

}