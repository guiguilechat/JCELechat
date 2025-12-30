package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute for MJD/MJFG modules which determines how long ships are warp scrambled for after having been jumped. If set to zero, no warp scrambling will occur.  Value is in milliseconds
 */
public class MjdPostActivationScramDuration
    extends IntAttribute
{
    public static final MjdPostActivationScramDuration INSTANCE = new MjdPostActivationScramDuration();

    @Override
    public int getId() {
        return  5687;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "MjdPostActivationScramDuration";
    }
}
