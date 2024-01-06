package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Cycle time for a missile launch, in milliseconds.
 */
public class MissileLaunchDuration
    extends RealAttribute
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
    public Number getDefaultValue() {
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
