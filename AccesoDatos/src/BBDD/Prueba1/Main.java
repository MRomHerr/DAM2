package BBDD.Prueba1;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Establecer conexión con la base de datos
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/prueba1", "prueba1", "prueba1"
            );

            // Preparar la consulta para obtener los datos de la tabla "notas"
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM notas";
            ResultSet resul = sentencia.executeQuery(sql);

            // Recorrer el resultado y mostrar cada fila
            System.out.println("IDNota | CodAlumno | NombreAsig | Nota");
            while (resul.next()) {
                System.out.printf("%d | %d | %s | %.2f %n",
                        resul.getInt("idnota"),
                        resul.getInt("codalumno"),
                        resul.getString("nombreasig"),
                        resul.getFloat("nota")
                );
            }

            // Cerrar ResultSet, Statement y Connection
            resul.close();
            sentencia.close();
            conexion.close();

        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // fin de main
} // fin de la clase
