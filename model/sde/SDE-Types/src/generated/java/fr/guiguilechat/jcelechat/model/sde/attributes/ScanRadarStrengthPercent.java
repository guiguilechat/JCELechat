package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ScanRadarStrengthPercent
    extends IntAttribute
{
    public static final ScanRadarStrengthPercent INSTANCE = new ScanRadarStrengthPercent();

    @Override
    public int getId() {
        return  1030;
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
        return true;
    }

    @Override
    public String toString() {
        return "ScanRadarStrengthPercent";
    }
}
