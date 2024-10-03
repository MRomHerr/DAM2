package tema1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class EjecutarVisualizarCadena {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduce una cadena: ");
        try {
            String cadena = reader.readLine();
            for (int i = 0; i < 5; i++) {
                String[] cmd = {"java", "tema1.VisualizarCadena", cadena};
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
