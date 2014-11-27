import environnement.structure.Map;
import vehicule.Vehicule;

import java.util.Scanner;

/**
 * Created by clément on 23/11/2014.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Map map = new Map();

        map.startFeux();

        Vehicule v1 = new Vehicule(map.getRoute(13).getLastMaillon(), 2000, "v1");
        Vehicule v2 = new Vehicule(map.getRoute(18).getFirstMaillon(), 2000, "v2");

        Scanner sc = new Scanner(System.in);

        v1.start();
        v2.start();

        sc.next();

        v1.interrupt();
        v2.interrupt();

        v1.join();
        v2.join();
        map.stopFeux();
    }
}
