package environnement.signalisation;

import environnement.maillon.Maillon;

/**
 * Created by clément on 25/11/2014.
 */
public class Feu extends Thread {

    private Maillon maillon;
    private int dureeFeu;

    public Feu(Maillon maillon, int dureeFeu, boolean startAtVert) {
        this.maillon = maillon;
        this.dureeFeu = dureeFeu;
        if(startAtVert) {
            maillon.accessible();
        } else {
            maillon.inaccessible();
        }
    }

    public void changerFeu() {
        if (maillon.isAccessible()) {
            maillon.inaccessible();
        } else {
            maillon.accessible();
        }
    }

    public void run() {
        try {
            while(!isInterrupted()) {
                System.out.println(toString());
                sleep(dureeFeu);
                changerFeu();
            }
        } catch (InterruptedException e) {

        }
    }

    public String couleurFeu() {
        if(maillon.isAccessible()) {
            return "vert";
        }
        return "rouge";
    }

    @Override
    public String toString() {
        return maillon.getIdentifiant() + " => FEU " + couleurFeu() + "!" ;
    }
}