package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Factor to scale mining laser durations by.
 */
public class MiningDurationMultiplier
    extends DoubleAttribute
{
    public static final MiningDurationMultiplier INSTANCE = new MiningDurationMultiplier();

    @Override
    public int getId() {
        return  203;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MiningDurationMultiplier";
    }
}
