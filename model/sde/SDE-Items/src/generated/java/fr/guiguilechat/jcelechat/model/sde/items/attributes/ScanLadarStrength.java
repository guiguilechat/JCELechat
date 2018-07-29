package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Ladar strength.
 */
public class ScanLadarStrength
    extends DoubleAttribute
{
    public final static ScanLadarStrength INSTANCE = new ScanLadarStrength();

    @Override
    public int getId() {
        return  209;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanLadarStrength";
    }
}
