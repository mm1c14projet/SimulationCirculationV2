package environnement.structure.carrefour;

/**
 * Created by clément on 23/11/2014.
 */

import environnement.maillon.Maillon;
import environnement.structure.Route;

/**
 *      Carrefour pour lequel une voiture entrant sur le carrefour ressortira par une seule route:
 *
 *   ← rExtE ← mExt3 ← mExt2
 *                        ↑
 *   → rIntE → mInt   mExt1
 *               ↓       ↑
 *             rIntS  rExtE
 *              ↓       ↑
 *
 */
public class CarrefourUniDirectionnel {
    private Maillon maillonInt;
    private Maillon maillonExt1;
    private Maillon maillonExt2;
    private Maillon maillonExt3;

    public CarrefourUniDirectionnel(Route routeInterieurEntrante, Route routeInterieurSortante,
                                    Route routeExterieurEntrante, Route routeExterieurSortante,
                                    int numUniDir) {
        maillonInt = new Maillon(false, true, "carrUniDir[" + numUniDir + "]:maillonInterieur");
        maillonExt1 = new Maillon(false, true, "carrUniDir[" + numUniDir + "]:maillonExterieur1");
        maillonExt2 = new Maillon(false, true, "carrUniDir[" + numUniDir + "]:maillonExterieur2");
        maillonExt3 = new Maillon(false, true, "carrUniDir[" + numUniDir + "]:maillonExterieur3");

        routeInterieurEntrante.linkLastMaillon(maillonInt);
        maillonInt.addNextMaillon(routeInterieurSortante.getFirstMaillon());

        routeExterieurEntrante.linkLastMaillon(maillonExt1);
        maillonExt1.addNextMaillon(maillonExt2);
        maillonExt2.addNextMaillon(maillonExt3);
        maillonExt3.addNextMaillon(routeExterieurSortante.getFirstMaillon());
    }
}

