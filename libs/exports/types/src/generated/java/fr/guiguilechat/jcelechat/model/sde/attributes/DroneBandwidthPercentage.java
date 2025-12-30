package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class DroneBandwidthPercentage
    extends IntAttribute
{
    public static final DroneBandwidthPercentage INSTANCE = new DroneBandwidthPercentage();

    @Override
    public int getId() {
        return  3124;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "DroneBandwidthPercentage";
    }
}
