package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Expedition Hold Capacity
 */
public class SpecialExpeditionHoldCapacity
    extends IntAttribute
{
    public static final SpecialExpeditionHoldCapacity INSTANCE = new SpecialExpeditionHoldCapacity();

    @Override
    public int getId() {
        return  5944;
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
        return false;
    }

    @Override
    public String toString() {
        return "SpecialExpeditionHoldCapacity";
    }
}
