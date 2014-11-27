package environnement.structure.carrefour;

import environnement.maillon.Maillon;
import environnement.structure.Route;

/**
 * Created by clément on 23/11/2014.
 */

/**
 *      Carrefour à 4 voies:
 *
 *             rUE   rUS
 *              ↓     ↑
 *      rLS ← mHG ← mHD ← rRE
 *             ↓     ↑
 *     rLE → mBG → mBD → rRS
 *            ↓      ↑
 *           rDS    rDE
 *
 */

public class CarrefourTriDirectionnel {
    private Maillon maillonHautGauche;
    private Maillon maillonHautDroit;
    private Maillon maillonBasGauche;
    private Maillon maillonBasDroit;

    public CarrefourTriDirectionnel(Route routeUpEntrante,    Route routeUpSortante,
                                    Route routeRightEntrante, Route routeRightSortante,
                                    Route routeDownEntrante,  Route routeDownSortante,
                                    Route routeLeftEntrante,  Route routeLeftSortante,
                                    boolean hasFeux, int numTriDir) {
        maillonHautGauche = new Maillon(hasFeux, true, "triDir[" + numTriDir + "]:maillonHautGauche");
        maillonHautDroit = new Maillon(hasFeux, true, "triDir[" + numTriDir + "]:maillonHautDroit");
        maillonBasGauche = new Maillon(hasFeux, true, "triDir[" + numTriDir + "]:maillonBasGauche");
        maillonBasDroit = new Maillon(hasFeux, true, "triDir[" + numTriDir + "]:maillonBasDroit");

        maillonHautGauche.addNextMaillon(maillonBasGauche);
        maillonBasGauche.addNextMaillon(maillonBasDroit);
        maillonBasDroit.addNextMaillon(maillonHautDroit);
        maillonHautDroit.addNextMaillon(maillonHautGauche);

        routeUpEntrante.linkLastMaillon(maillonHautGauche);
        routeRightEntrante.linkLastMaillon(maillonHautDroit);
        routeDownEntrante.linkLastMaillon(maillonBasDroit);
        routeLeftEntrante.linkLastMaillon(maillonBasGauche);

        maillonHautGauche.addNextMaillon(routeLeftSortante.getFirstMaillon());
        maillonHautDroit.addNextMaillon(routeUpSortante.getFirstMaillon());
        maillonBasDroit.addNextMaillon(routeRightSortante.getFirstMaillon());
        maillonBasGauche.addNextMaillon(routeDownSortante.getFirstMaillon());
    }

    public Maillon getMaillonHautGauche() {
        return maillonHautGauche;
    }

    public Maillon getMaillonHautDroit() {
        return maillonHautDroit;
    }

    public Maillon getMaillonBasGauche() {
        return maillonBasGauche;
    }

    public Maillon getMaillonBasDroit() {
        return maillonBasDroit;
    }
}
