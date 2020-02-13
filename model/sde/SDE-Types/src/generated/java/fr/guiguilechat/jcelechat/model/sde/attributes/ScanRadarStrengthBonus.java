package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * +/- modifier to the radar strength of an electronic system.
 */
public class ScanRadarStrengthBonus
    extends DoubleAttribute
{
    public static final ScanRadarStrengthBonus INSTANCE = new ScanRadarStrengthBonus();

    @Override
    public int getId() {
        return  241;
    }

    @Override
    public int getCatId() {
        return  25;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanRadarStrengthBonus";
    }
}
