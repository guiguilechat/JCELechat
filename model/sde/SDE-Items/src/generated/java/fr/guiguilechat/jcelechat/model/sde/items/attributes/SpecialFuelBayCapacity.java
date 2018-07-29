package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * special fuel bay capacity
 */
public class SpecialFuelBayCapacity
    extends IntAttribute
{
    public final static SpecialFuelBayCapacity INSTANCE = new SpecialFuelBayCapacity();

    @Override
    public int getId() {
        return  1549;
    }

    @Override
    public int getCatId() {
        return  40;
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
