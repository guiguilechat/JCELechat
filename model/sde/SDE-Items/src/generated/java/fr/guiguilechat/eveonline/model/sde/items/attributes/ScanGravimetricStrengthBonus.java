package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * +/- modifier to the gravimetric strength of an electronic system.
 */
public class ScanGravimetricStrengthBonus
    extends DoubleAttribute
{
    public final static ScanGravimetricStrengthBonus INSTANCE = new ScanGravimetricStrengthBonus();

    @Override
    public int getId() {
        return  238;
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
        return "ScanGravimetricStrengthBonus";
    }
}
