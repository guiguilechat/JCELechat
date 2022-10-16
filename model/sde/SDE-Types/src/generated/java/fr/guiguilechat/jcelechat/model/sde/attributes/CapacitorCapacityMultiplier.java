package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class CapacitorCapacityMultiplier
    extends RealAttribute
{
    public static final CapacitorCapacityMultiplier INSTANCE = new CapacitorCapacityMultiplier();

    @Override
    public int getId() {
        return  147;
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
        return "CapacitorCapacityMultiplier";
    }
}
