package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * +/- modifier to the radar strength of an electronic system.
 */
public class ScanRadarStrengthBonus
    extends RealAttribute
{
    public static final ScanRadarStrengthBonus INSTANCE = new ScanRadarStrengthBonus();

    @Override
    public int getId() {
        return  241;
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
        return "ScanRadarStrengthBonus";
    }
}
