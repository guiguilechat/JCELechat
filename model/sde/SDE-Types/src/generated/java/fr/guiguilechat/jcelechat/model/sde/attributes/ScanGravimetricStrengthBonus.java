package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * +/- modifier to the gravimetric strength of an electronic system.
 */
public class ScanGravimetricStrengthBonus
    extends DoubleAttribute
{
    public static final ScanGravimetricStrengthBonus INSTANCE = new ScanGravimetricStrengthBonus();

    @Override
    public int getId() {
        return  238;
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
