package environnement.signalisation;

import environnement.structure.carrefour.CarrefourBiDirectionnel;
import environnement.structure.carrefour.CarrefourTriDirectionnel;

/**
 * Created by kukugath on 10/12/2014.
 */
public class FactoryFeu extends AFactoryFeu {

    private static FactoryFeu instance;

    @Override
    public FeuCarrefourBiDirectionnel createFeuBiDirectionnel(CarrefourBiDirectionnel carrefour, int dureeFeu) {
        return new FeuCarrefourBiDirectionnel(carrefour,dureeFeu);
    }

    @Override
    public FeuCarrefourTriDirectionnel createFeuTriDirectionnel(CarrefourTriDirectionnel carrefour, int dureeFeu) {
        return new FeuCarrefourTriDirectionnel(carrefour,dureeFeu);
    }

    //constructeur doit être privé
    private FactoryFeu() {
    }

    //Instance de la factory
    public final static FactoryFeu getInstance(){

        if(instance == null)
        {
            return new FactoryFeu();
        }
        return instance;
    }


}
