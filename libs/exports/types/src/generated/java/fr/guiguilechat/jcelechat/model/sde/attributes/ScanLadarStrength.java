package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Ladar strength.
 */
public class ScanLadarStrength
    extends RealAttribute
{
    public static final ScanLadarStrength INSTANCE = new ScanLadarStrength();

    @Override
    public int getId() {
        return  209;
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
        return "ScanLadarStrength";
    }
}
