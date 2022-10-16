package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range that an ECM burst has an effect within.
 */
public class EcmBurstRange
    extends IntAttribute
{
    public static final EcmBurstRange INSTANCE = new EcmBurstRange();

    @Override
    public int getId() {
        return  142;
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
        return "EcmBurstRange";
    }
}
