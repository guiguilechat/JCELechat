package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Capacity of ice-only hold
 */
public class SpecialIceHoldCapacity
    extends IntAttribute
{
    public static final SpecialIceHoldCapacity INSTANCE = new SpecialIceHoldCapacity();

    @Override
    public int getId() {
        return  3136;
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
        return "SpecialIceHoldCapacity";
    }
}
