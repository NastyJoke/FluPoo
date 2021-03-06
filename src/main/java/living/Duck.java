package living;

import enu.State;
import sicknesses.H5N1;
import sicknesses.Sickness;

/**
 * Created by renaud on 26/11/2015.
 */
public class Duck extends Animal {

    public Duck()
    {
        sicknesses = new Sickness[1];
        sicknesses[0] = new H5N1();
        if (this.isSickChance < this.sickRate) {
            this.state = State.SICK;
            sicknesses[0].enable();
            this.daysToWait = getActiveSickness().getIncubationTime();
        }
        else
            this.state = State.HEALTHY;
    }


    public String toString()
    {
        /*if (this.state.equals(State.HEALTHY))
            return "Duck";
        else if (this.state.equals(State.DEAD))
            return "DCKX";
        else
            return "Dck"+daysToWait;*/
        return "Duck";
    }

}
