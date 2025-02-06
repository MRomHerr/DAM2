package BBDD.NeoDatis;

import org.neodatis.odb.*;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;

public class EjemploNeodatis {
    public static void main(String[] args) {
        ODB odb = null;
        try {
            // Crear instancias para almacenar en BD
            Jugadores j1 = new Jugadores("Maria", "voleibol", "Madrid", 14);
            Jugadores j2 = new Jugadores("Miguel", "tenis", "Madrid", 15);
            Jugadores j3 = new Jugadores("Mario", "baloncesto", "Guadalajara", 15);
            Jugadores j4 = new Jugadores("Alicia", "tenis", "Madrid", 14);

            Paises p1 = new Paises(45894527, "España");
            Paises p2 = new Paises(45894527, "España");
            Paises p3 = new Paises(45894527, "España");
            Paises p4 = new Paises(45894527, "España");

            odb = ODBFactory.open("neodatis.test"); // Abrir BD

            // Almacenamos objetos
            odb.store(j1);
            odb.store(p1);
            odb.store(j2);
            odb.store(p2);
            odb.store(j3);
            odb.store(p3);
            odb.store(j4);
            odb.store(p4);

            // Recuperamos todos los objetos
            Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
            Objects<Paises> objects2 = odb.getObjects(Paises.class);
            System.out.printf("%d Jugadores, %d Paises:%n", objects.size(), objects2.size());

            // Visualizar los jugadores
            System.out.println("\nJugadores:");
            int i = 1;
            while (objects.hasNext()) {
                Jugadores jugador = objects.next();
                System.out.printf("Jugador %d: %s, %s, %s, %d años%n",
                        i++, jugador.getNombre(),
                        jugador.getDeporte(),
                        jugador.getCiudad(),
                        jugador.getEdad());
            }

            // Visualizar los países
            System.out.println("\nPaises:");
            i = 1;
            while (objects2.hasNext()) {
                Paises pais = objects2.next();
                System.out.printf("País %d: %s, ID: %d%n",
                        i++, pais.getNombrePais(), pais.getIdPais());
            }

            // Actualizar un jugador
            IQuery query = new CriteriaQuery(Jugadores.class, Where.equal("nombre", "Maria"));
            Objects<Jugadores> objetos = odb.getObjects(query);
            Jugadores jug = (Jugadores) objetos.getFirst();
            jug.setDeporte("vóley-playa");
            odb.store(jug);
            odb.commit();

            System.out.println("\nJugador actualizado:");
            System.out.printf("%s, %s%n", jug.getNombre(), jug.getDeporte());

            // Eliminar un jugador
            odb.delete(jug);
            odb.commit();
            System.out.println("Jugador eliminado: " + jug.getNombre());

            // Consulta por edad
            ICriterion criterio = Where.equal("edad", 14);
            query = new CriteriaQuery(Jugadores.class, criterio);
            Objects<Jugadores> jugadoresDe14 = odb.getObjects(query);

            System.out.println("\nJugadores de 14 años:");
            while (jugadoresDe14.hasNext()) {
                Jugadores jugador = jugadoresDe14.next();
                System.out.printf("%s, %d años%n", jugador.getNombre(), jugador.getEdad());
            }

        } catch (Exception e) {
            System.out.println("Error en la operación de base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (odb != null) {
                odb.close(); // Cerrar la BD
            }
        }
    }
}
