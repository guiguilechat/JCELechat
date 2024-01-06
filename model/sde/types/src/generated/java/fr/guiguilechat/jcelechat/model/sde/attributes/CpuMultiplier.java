package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Factor to adjust module cpu need by.
 */
public class CpuMultiplier
    extends RealAttribute
{
    public static final CpuMultiplier INSTANCE = new CpuMultiplier();

    @Override
    public int getId() {
        return  202;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "CpuMultiplier";
    }
}
