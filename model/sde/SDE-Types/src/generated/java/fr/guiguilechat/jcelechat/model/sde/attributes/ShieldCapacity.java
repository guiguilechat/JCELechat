package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Amount of maximum shield HP on the item.
 */
public class ShieldCapacity
    extends RealAttribute
{
    public static final ShieldCapacity INSTANCE = new ShieldCapacity();

    @Override
    public int getId() {
        return  263;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return true;
    }

    @Override
    public String toString() {
        return "ShieldCapacity";
    }
}
