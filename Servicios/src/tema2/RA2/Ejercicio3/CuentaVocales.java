package tema2.RA2.Ejercicio3;

/**
 * clase que cuenta las vocales en un texto utilizando varios hilos.
 * cada hilo cuenta un conjunto de vocales (aáAÁ, eéEÉ, iíIÍ, oóOÓ, uúUÚ).
 *
 * @author Marcos Romero Herrero
 * @version 1.0
 * @date 11/11/2024
 */
public class CuentaVocales {
    private static int totalVocales = 0; // total de vocales contadas
    private static final String[] vocales = {"aáAÁ", "eéEÉ", "iíIÍ", "oóOÓ", "uúUÚ"}; // conjuntos de vocales con tildes y mayusculas
    private final String texto; // texto en el que se contaran las vocales

    /**
     * constructor que inicializa el texto en el que se contaran las vocales.
     *
     * @param texto el texto en el que se contaran las vocales
     */
    public CuentaVocales(String texto) {
        this.texto = texto;
    }//fin constructor CuentaVocales

    /**
     * clase que implementa la interfaz runnable para contar las vocales
     * de un conjunto especificado en el texto.
     */
    private class Contador implements Runnable {
        private final String conjuntoVocales; // conjunto de vocales a contar

        /**
         * constructor que inicializa el conjunto de vocales a contar.
         *
         * @param conjuntoVocales el conjunto de vocales que se contaran
         */
        public Contador(String conjuntoVocales) {
            this.conjuntoVocales = conjuntoVocales;
        }

        /**
         * metodo que cuenta las vocales del conjunto en el texto y actualiza el total
         * de vocales de forma sincronizada.
         */
        @Override
        public void run() {
            int count = 0; // contador de vocales
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i); // obtener el caracter en la posicion i
                if (conjuntoVocales.indexOf(c) != -1) { // comprobar si el caracter es una vocal
                    count++; // incrementar el contador de vocales
                }
            }
            //sincronizar el acceso a la variable totalVocales
            synchronized (CuentaVocales.this) {
                totalVocales += count; // sumar al total de vocales
            }
            //imprimir el conteo de vocales por conjunto
            System.out.println("hilo para las vocales '" + conjuntoVocales + "' conto " + count + " vocales.");
        }//fin metodo run
    }//fin Contador

    /**
     * metodo que crea y ejecuta los hilos para contar las vocales en el texto.
     * al finalizar, muestra el total de vocales encontradas.
     */
    public void contarVocales() {
        Thread[] hilos = new Thread[5]; // arreglo para los hilos

        //crear y arrancar los hilos para contar las vocales
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Contador(vocales[i])); // crear el hilo para cada conjunto de vocales
            hilos[i].start(); // arrancar el hilo
        }

        //esperar a que todos los hilos terminen
        for (Thread hilo : hilos) {
            try {
                hilo.join(); //esperar a que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace(); //imprimir error si se interrumpe el hilo
            }
        }

        //imprimir el total de vocales contadas
        System.out.println("total de vocales: " + totalVocales); // mostrar el total
    }// fin metodo contarVocales

    /**
     * metodo principal donde se define el texto y se inicia el conteo de vocales.
     *
     * @param args los argumentos del programa (no utilizados)
     */
    public static void main(String[] args) {
        String texto = "este es un texto de ejemplo para contar vocales utilizando multiples hilos, incluyendo tildes y mayusculas."; // texto de ejemplo
        CuentaVocales contador = new CuentaVocales(texto); //crear instancia de CuentaVocales
        contador.contarVocales(); //llamar al metodo para contar las vocales
    }//fin main
}//fin clase
