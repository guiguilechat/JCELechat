package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * special fuel bay capacity
 */
public class SpecialFuelBayCapacity
    extends IntAttribute
{
    public static final SpecialFuelBayCapacity INSTANCE = new SpecialFuelBayCapacity();

    @Override
    public int getId() {
        return  1549;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "SpecialFuelBayCapacity";
    }
}
