package environnement.structure.carrefour;

/**
 * Created by clément on 23/11/2014.
 */

import environnement.maillon.Maillon;
import environnement.structure.Route;

/**
 *      Carrefour pour lequel une voiture entrant sur le carrefour "choisir" entre deux directions
 *    différentes:
 *
 *
 *     ← rExtS ← mExtS ← mExtE ← rExtE ←
 *                ↓        ↑
 *     → rIntE → mIntE → mIntS → rIntS →
 *                ↓        ↑
 *             rPerpS   rPerpE
 *                ↓       ↑

 */
public class CarrefourBiDirectionnel {
    private Maillon maillonExtEntrant;
    private Maillon maillonExtSortant;
    private Maillon maillonIntEntrant;
    private Maillon maillonIntSortant;

    public CarrefourBiDirectionnel(Route routeExterieurEntrante, Route routeExterieurSortante,
                                   Route routeInterieurEntrante, Route routeInterieurSortante,
                                   Route routePerpEntrante,      Route routePerpSortante,
                                   boolean hasFeux, int numBiDir) {
        maillonExtEntrant = new Maillon(hasFeux, true, "biDir[" + numBiDir + "]:maillonExtEntrant");
        maillonExtSortant = new Maillon(hasFeux, true, "biDir[" + numBiDir + "]:maillonExtSortant");
        maillonIntEntrant = new Maillon(hasFeux, true, "biDir[" + numBiDir + "]:maillonIntEntrant");
        maillonIntSortant = new Maillon(hasFeux, true, "biDir[" + numBiDir + "]:maillonIntSortant");

        maillonExtEntrant.addNextMaillon(maillonExtSortant);
        maillonExtSortant.addNextMaillon(maillonIntEntrant);
        maillonIntEntrant.addNextMaillon(maillonIntSortant);
        maillonIntSortant.addNextMaillon(maillonExtEntrant);

        routeExterieurEntrante.linkLastMaillon(maillonExtEntrant);
        maillonExtSortant.addNextMaillon(routeExterieurSortante.getFirstMaillon());
        routeInterieurEntrante.linkLastMaillon(maillonIntEntrant);
        maillonIntSortant.addNextMaillon(routeInterieurSortante.getFirstMaillon());
        routePerpEntrante.linkLastMaillon(maillonIntSortant);
        maillonIntEntrant.addNextMaillon(routePerpSortante.getFirstMaillon());
    }

    public Maillon getMaillonExtEntrant() {
        return maillonExtEntrant;
    }

    public Maillon getMaillonExtSortant() {
        return maillonExtSortant;
    }

    public Maillon getMaillonIntEntrant() {
        return maillonIntEntrant;
    }

    public Maillon getMaillonIntSortant() {
        return maillonIntSortant;
    }
}
