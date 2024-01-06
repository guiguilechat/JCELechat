package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * CPU Usage per kilometer
 */
public class CpuLoadPerKm
    extends RealAttribute
{
    public static final CpuLoadPerKm INSTANCE = new CpuLoadPerKm();

    @Override
    public int getId() {
        return  1634;
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
        return "CpuLoadPerKm";
    }
}
