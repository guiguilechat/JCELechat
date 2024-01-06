package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Radar strength.
 */
public class ScanRadarStrength
    extends RealAttribute
{
    public static final ScanRadarStrength INSTANCE = new ScanRadarStrength();

    @Override
    public int getId() {
        return  208;
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
        return "ScanRadarStrength";
    }
}
