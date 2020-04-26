package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * special booster hold capacity
 */
public class SpecialBoosterHoldCapacity
    extends IntAttribute
{
    public static final SpecialBoosterHoldCapacity INSTANCE = new SpecialBoosterHoldCapacity();

    @Override
    public int getId() {
        return  2657;
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
        return "SpecialBoosterHoldCapacity";
    }
}
