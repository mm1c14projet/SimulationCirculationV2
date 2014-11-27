package vehicule;

import environnement.maillon.Maillon;

/**
 * Created by clément on 24/11/2014.
 */
public class Vehicule extends Thread {
    private Maillon position;
    private int vitesse = 1000;
    private String identifiant; //Pour la représentation

    public Vehicule(Maillon maillon, int vitesse, String identifiant) {
        position = maillon;
        position.vehiculeIn();
        this.vitesse = vitesse;
        this.identifiant = identifiant;
    }

    public void avancer() {
        Maillon nextPosition = position.getNextMaillon(),
                tmp = position;

        if(position.isMaillonCarrefour() || !nextPosition.hasFeux()) {
            nextPosition.attenteMaillonLibre();
        } else {
            nextPosition.attenteMaillonLibreEtAccessible();
        }
        position = nextPosition;
        tmp.vehiculeOut();
        System.out.println(identifiant + " => " + position.getIdentifiant());
    }

    public void run()
    {
        try {
            System.out.println(identifiant +
                               ": Debut Du Parcours sur: " + position.getIdentifiant());
            while(!isInterrupted()) {
                avancer();
                sleep(vitesse);
            }
        } catch (InterruptedException e) {
            position.vehiculeOut();
            System.out.println(identifiant + ": Fin Du Parcours!");
        }
    }

    public Maillon getPosition() {
        return position;
    }

    public String getIdentifiant() {
        return identifiant;
    }
}
