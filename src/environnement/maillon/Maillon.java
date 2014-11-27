package environnement.maillon;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by clément on 23/11/2014.
 */
public class Maillon {
    private boolean isEmpty;
    private final boolean hasFeux;
    private boolean isAccessible;
    private boolean isMaillonCarrefour;
    private ArrayList<Maillon> nextMaillons;
    private String identifiant;  // Pour la représentation

    public Maillon(boolean hasFeux, boolean isMaillonCarrefour, String identifiant) {
        isEmpty = true;
        this.hasFeux = hasFeux;
        isAccessible = true;
        this.isMaillonCarrefour = isMaillonCarrefour;
        nextMaillons = new ArrayList<Maillon>();
        this.identifiant = identifiant;
    }

    public void addNextMaillon(Maillon maillon) {
        nextMaillons.add(maillon);
    }

    public Maillon getNextMaillon() {
        Random random = new Random();
        return nextMaillons.get(random.nextInt(nextMaillons.size()));
    }

    public void vehiculeIn() {
        isEmpty = false;
    }

    public synchronized void vehiculeOut() {
        isEmpty = true;
        notifyAll();
    }

    public synchronized void attenteMaillonLibre() {
        while(!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        vehiculeIn();
    }

    public synchronized void attenteMaillonLibreEtAccessible() {
        while(!isEmpty || !isAccessible) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        vehiculeIn();
    }

    public boolean hasFeux() {
        return hasFeux;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void inaccessible() {
        isAccessible = false;
    }

    public synchronized void accessible() {
        isAccessible = true;
        notifyAll();
    }

    public boolean isMaillonCarrefour() {
        return isMaillonCarrefour;
    }

    public String getIdentifiant() {
        return identifiant;
    }
}
