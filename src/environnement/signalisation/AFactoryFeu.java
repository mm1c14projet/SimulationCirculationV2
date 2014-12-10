package environnement.signalisation;

import environnement.signalisation.FeuCarrefourBiDirectionnel;
import environnement.signalisation.FeuCarrefourTriDirectionnel;
import environnement.structure.carrefour.CarrefourBiDirectionnel;
import environnement.structure.carrefour.CarrefourTriDirectionnel;

/**
 * Created by kukugath on 10/12/2014.
 */
public abstract class AFactoryFeu {


    public abstract FeuCarrefourBiDirectionnel createFeuBiDirectionnel(CarrefourBiDirectionnel carrefour, int dureeFeu);

    public abstract FeuCarrefourTriDirectionnel createFeuTriDirectionnel(CarrefourTriDirectionnel carrefour,int dureeFeu);



}
