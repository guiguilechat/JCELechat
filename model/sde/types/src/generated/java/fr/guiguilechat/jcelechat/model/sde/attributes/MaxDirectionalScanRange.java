package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Maximum range (in metres) that a ship's Directional Scanner can reach
 */
public class MaxDirectionalScanRange
    extends RealAttribute
{
    public static final MaxDirectionalScanRange INSTANCE = new MaxDirectionalScanRange();

    @Override
    public int getId() {
        return  5796;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  2.147483648E12;
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
        return "MaxDirectionalScanRange";
    }
}
