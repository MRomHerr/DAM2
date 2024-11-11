package tema2.RA2;

public class CuentaVocales {
    private static int totalVocales = 0;
    private static final String VOCALES = "aeiou";
    private final String texto;

    public CuentaVocales(String texto) {
        this.texto = texto.toLowerCase();
    }

    private class ContadorVocal implements Runnable {
        private final char vocal;

        public ContadorVocal(char vocal) {
            this.vocal = vocal;
        }

        @Override
        public void run() {
            int count = 0;
            for (int i = 0; i < texto.length(); i++) {
                if (texto.charAt(i) == vocal) {
                    count++;
                }
            }
            synchronized (CuentaVocales.this) {
                totalVocales += count;
            }
            System.out.println("Hilo para '" + vocal + "' contó " + count + " vocales.");
        }
    }

    public void contarVocales() {
        Thread[] hilos = new Thread[5];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new ContadorVocal(VOCALES.charAt(i)));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total de vocales: " + totalVocales);
    }

    public static void main(String[] args) {
        String texto = "Este es un texto de ejemplo para contar vocales utilizando múltiples hilos.";
        CuentaVocales contador = new CuentaVocales(texto);
        contador.contarVocales();
    }
}