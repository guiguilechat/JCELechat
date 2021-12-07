package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This multiplier is applied to the Mining Volume of the actor (module, drone etc.) to calculate the potential wasted volume per cycle
 */
public class MiningWastedVolumeMultiplier
    extends IntAttribute
{
    public static final MiningWastedVolumeMultiplier INSTANCE = new MiningWastedVolumeMultiplier();

    @Override
    public int getId() {
        return  3153;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "MiningWastedVolumeMultiplier";
    }
}
