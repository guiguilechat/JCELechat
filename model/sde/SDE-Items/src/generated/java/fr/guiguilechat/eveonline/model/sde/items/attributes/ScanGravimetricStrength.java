package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Gravimetric strength.
 */
public class ScanGravimetricStrength
    extends DoubleAttribute
{
    public final static ScanGravimetricStrength INSTANCE = new ScanGravimetricStrength();

    @Override
    public int getId() {
        return  211;
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
        return "ScanGravimetricStrength";
    }
}
