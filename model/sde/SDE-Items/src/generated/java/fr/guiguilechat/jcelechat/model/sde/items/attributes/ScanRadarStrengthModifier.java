package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * + / - modifier to a ship radar strength
 */
public class ScanRadarStrengthModifier
    extends IntAttribute
{
    public final static ScanRadarStrengthModifier INSTANCE = new ScanRadarStrengthModifier();

    @Override
    public int getId() {
        return  1565;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "ScanRadarStrengthModifier";
    }
}
