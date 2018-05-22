package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * +/- modifier to the ladar strength of an electronic system.
 */
public class ScanLadarStrengthBonus
    extends DoubleAttribute
{
    public final static ScanLadarStrengthBonus INSTANCE = new ScanLadarStrengthBonus();

    @Override
    public int getId() {
        return  239;
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
        return "ScanLadarStrengthBonus";
    }
}
