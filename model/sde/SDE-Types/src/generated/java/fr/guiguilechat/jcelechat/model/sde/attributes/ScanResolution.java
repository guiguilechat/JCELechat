package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The resolution that the vessel can target other objects at.
 */
public class ScanResolution
    extends DoubleAttribute
{
    public static final ScanResolution INSTANCE = new ScanResolution();

    @Override
    public int getId() {
        return  564;
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
        return "ScanResolution";
    }
}
