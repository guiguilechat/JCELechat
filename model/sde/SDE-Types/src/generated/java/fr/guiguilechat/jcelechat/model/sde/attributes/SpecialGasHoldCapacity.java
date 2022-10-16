package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of gas-only hold
 */
public class SpecialGasHoldCapacity
    extends IntAttribute
{
    public static final SpecialGasHoldCapacity INSTANCE = new SpecialGasHoldCapacity();

    @Override
    public int getId() {
        return  1557;
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
        return "SpecialGasHoldCapacity";
    }
}
