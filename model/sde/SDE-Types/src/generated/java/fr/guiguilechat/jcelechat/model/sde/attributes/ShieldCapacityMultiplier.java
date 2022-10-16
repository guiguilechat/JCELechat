package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier to the capacity of a shield.
 */
public class ShieldCapacityMultiplier
    extends RealAttribute
{
    public static final ShieldCapacityMultiplier INSTANCE = new ShieldCapacityMultiplier();

    @Override
    public int getId() {
        return  146;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "ShieldCapacityMultiplier";
    }
}
