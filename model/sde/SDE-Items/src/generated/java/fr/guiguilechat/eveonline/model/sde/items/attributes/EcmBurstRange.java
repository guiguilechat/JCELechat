package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Range that an ECM burst has an effect within.
 */
public class EcmBurstRange
    extends IntAttribute
{
    public final static EcmBurstRange INSTANCE = new EcmBurstRange();

    @Override
    public int getId() {
        return  142;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return true;
    }

    @Override
    public String toString() {
        return "EcmBurstRange";
    }
}
