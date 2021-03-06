package living;

import enu.State;
import sicknesses.H1N1;
import sicknesses.H5N1;
import sicknesses.Sickness;

/**
 * Created by renaud on 26/11/2015.
 */
public class Pig  extends Animal {

    public Pig()
    {
        sicknesses = new Sickness[1];
        sicknesses[0] = new H1N1();
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
            return "Pigg";
        else if (this.state.equals(State.DEAD))
            return "PIGX";
        else
            return "Pig"+daysToWait;*/
        return "Pig";
    }


}
