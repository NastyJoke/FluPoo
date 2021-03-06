package sicknesses;

/**
 * Created by renaud on 03/12/2015.
 */
public class H5N1 extends Sickness {
    public H5N1()
    {
        this.incubationTime = 3;
        this.contagionTime = 6;
        this.recoverTime = 2;
        this.severity = 1.5;
        this.contagionRate = 0.1;
    }

    @Override
    public String toString() {
        return "H5N1{}";
    }
}
