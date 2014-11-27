package environnement.signalisation;

import environnement.structure.carrefour.CarrefourTriDirectionnel;

/**
 * Created by clément on 25/11/2014.
 */

/**
 *             rUE   rUS
 *              ↓     ↑
 *      rLS ← mHG ← mHD ← rRE
 *             ↓     ↑
 *     rLE → mBG → mBD → rRS
 *            ↓      ↑
 *           rDS    rDE
 */
public class FeuCarrefourTriDirectionnel implements IFeuCarrefour {
    private Feu feuSynchro1;
    private Feu feuSynchro2;
    private Feu feuOppose1;
    private Feu feuOppose2;

    public FeuCarrefourTriDirectionnel(CarrefourTriDirectionnel carrefour, int dureeFeu) {
        feuSynchro1 = new Feu(carrefour.getMaillonHautGauche(), dureeFeu, true);
        feuSynchro2 = new Feu(carrefour.getMaillonBasDroit(), dureeFeu, true);
        feuOppose1 = new Feu(carrefour.getMaillonHautDroit(), dureeFeu, false);
        feuOppose2 = new Feu(carrefour.getMaillonBasGauche(), dureeFeu, false);
    }

    @Override
    public void startFeux() {
        feuSynchro1.start();
        feuSynchro2.start();
        feuOppose1.start();
        feuOppose2.start();
    }

    @Override
    public void stopFeux() throws InterruptedException {
        feuSynchro1.interrupt();
        feuSynchro2.interrupt();
        feuOppose1.interrupt();
        feuOppose2.interrupt();

        feuSynchro1.join();
        feuSynchro2.join();
        feuOppose1.join();
        feuOppose2.join();
    }
}
