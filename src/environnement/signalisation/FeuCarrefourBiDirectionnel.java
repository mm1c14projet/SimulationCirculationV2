package environnement.signalisation;

import environnement.maillon.Maillon;
import environnement.structure.carrefour.CarrefourBiDirectionnel;

/**
 * Created by clément on 25/11/2014.
 */

/*
 *     ← rExtS ← mExtS ← mExtE ← rExtE ←
 *                ↓        ↑
 *     → rIntE → mIntE → mIntS → rIntS →
 *                ↓        ↑
 *             rPerpS   rPerpE
 *                ↓       ↑
 */

public class FeuCarrefourBiDirectionnel {
    private Feu feuSynchro1;
    private Feu feuSynchro2;
    private Feu feuOppose;

    public FeuCarrefourBiDirectionnel(CarrefourBiDirectionnel carrefour, int dureeFeu) {
        feuSynchro1 = new Feu(carrefour.getMaillonExtEntrant(), dureeFeu, true);
        feuSynchro2 = new Feu(carrefour.getMaillonIntEntrant(), dureeFeu, true);
        feuOppose = new Feu(carrefour.getMaillonIntSortant(), dureeFeu, false);
    }

    public void startFeux() {
        feuSynchro1.start();
        feuSynchro2.start();
        feuOppose.start();
    }

    public void stopFeux() throws InterruptedException {
        feuSynchro1.interrupt();
        feuSynchro2.interrupt();
        feuOppose.interrupt();

        feuSynchro1.join();
        feuSynchro2.join();
        feuOppose.join();
    }

}
