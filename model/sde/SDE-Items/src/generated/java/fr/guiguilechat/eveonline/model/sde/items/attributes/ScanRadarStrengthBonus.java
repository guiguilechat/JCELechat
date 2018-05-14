package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * +/- modifier to the radar strength of an electronic system.
 */
public class ScanRadarStrengthBonus
    extends DoubleAttribute
{
    public final static ScanRadarStrengthBonus INSTANCE = new ScanRadarStrengthBonus();

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
}
