package environnement.maillon;

import vehicule.Vehicule;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by clément on 23/11/2014.
 */
public class Maillon {
    private boolean isEmpty;
    private final boolean hasFeux;
    private final boolean hasStationEssence;
    private boolean isAccessible;
    private boolean isMaillonCarrefour;
    private ArrayList<Maillon> nextMaillons;
    private String identifiant;  // Pour la représentation

    public Maillon(boolean hasFeux, boolean isMaillonCarrefour, String identifiant, boolean hasStationEssence) {
        hasStationEssence = isMaillonCarrefour;
        isEmpty = true;
        this.hasFeux = hasFeux;
        this.hasStationEssence = hasStationEssence;
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

    public synchronized void attenteMaillonLibreEtAccessible(Vehicule vehicule) {
        while(!isEmpty || !isAccessible) {

            if(!isAccessible) {
                System.out.println("" + vehicule.getIdentifiant() + " bloqué au feu rouge " + vehicule.getPosition().getIdentifiant());

                try {
                    wait();
                }catch (InterruptedException e)
                {
                    System.out.println(e);
                }

                System.out.println("Feu vert vehicule : "+vehicule.getIdentifiant()+" repart");
            }
            else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }

        }

        vehiculeIn();
    }

    public boolean hasFeux() {
        return hasFeux;
    }

    public boolean hasStationEssence(){ return hasStationEssence;}

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
