package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier on droneBandwidth. The default value should be 0 to ensure that CONCORD NPCs can set the bandwidth of a target ship to 0.
 */
public class DroneBandwidthMultiplier
    extends DoubleAttribute
{
    public static final DroneBandwidthMultiplier INSTANCE = new DroneBandwidthMultiplier();

    @Override
    public int getId() {
        return  1328;
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
        return "DroneBandwidthMultiplier";
    }
}
