package me.ridiche.superSkibidiElytra.turbulence;

import me.ridiche.superSkibidiElytra.SuperSkibidiElytra;
import org.bukkit.util.Vector;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public abstract class RandomUnitVectorGenerator {
    // ok this is really stupid but bear with me
    public static Vector randomUnitVector() {
        Vector ret = null;
        while (ret == null) {
            ret = new Vector(
                    SuperSkibidiElytra.randomDouble()*2-1,
                    SuperSkibidiElytra.randomDouble()*2-1,
                    SuperSkibidiElytra.randomDouble()*2-1);
            // if we include vectors longer than 1, more vectors would appear in the corners
            if (ret.lengthSquared() == 0 || ret.lengthSquared() > 1)
                ret = null;
            else
                ret.multiply(1/ret.length());
        }
        return ret;
    }
}
