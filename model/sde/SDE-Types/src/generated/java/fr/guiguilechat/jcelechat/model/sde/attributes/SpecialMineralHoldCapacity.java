package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of mineral-only hold
 */
public class SpecialMineralHoldCapacity
    extends IntAttribute
{
    public static final SpecialMineralHoldCapacity INSTANCE = new SpecialMineralHoldCapacity();

    @Override
    public int getId() {
        return  1558;
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
        return "SpecialMineralHoldCapacity";
    }
}
