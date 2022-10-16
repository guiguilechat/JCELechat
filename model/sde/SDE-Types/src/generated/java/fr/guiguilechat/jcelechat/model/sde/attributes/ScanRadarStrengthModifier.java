package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * + / - modifier to a ship radar strength
 */
public class ScanRadarStrengthModifier
    extends IntAttribute
{
    public static final ScanRadarStrengthModifier INSTANCE = new ScanRadarStrengthModifier();

    @Override
    public int getId() {
        return  1565;
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
        return "ScanRadarStrengthModifier";
    }
}
