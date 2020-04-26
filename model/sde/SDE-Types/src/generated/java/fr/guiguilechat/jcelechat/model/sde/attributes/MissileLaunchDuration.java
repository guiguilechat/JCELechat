package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Cycle time for a missile launch, in milliseconds.
 */
public class MissileLaunchDuration
    extends DoubleAttribute
{
    public static final MissileLaunchDuration INSTANCE = new MissileLaunchDuration();

    @Override
    public int getId() {
        return  506;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  20000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MissileLaunchDuration";
    }
}
