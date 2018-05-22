package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Cycle time for a missile launch, in milliseconds.
 */
public class MissileLaunchDuration
    extends IntAttribute
{
    public final static MissileLaunchDuration INSTANCE = new MissileLaunchDuration();

    @Override
    public int getId() {
        return  506;
    }

    @Override
    public int getCatId() {
        return  30;
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
