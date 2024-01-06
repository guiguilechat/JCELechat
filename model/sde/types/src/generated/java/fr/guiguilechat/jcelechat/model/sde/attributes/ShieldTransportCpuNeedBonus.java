package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduction of CPU need of shield transporters.
 */
public class ShieldTransportCpuNeedBonus
    extends IntAttribute
{
    public static final ShieldTransportCpuNeedBonus INSTANCE = new ShieldTransportCpuNeedBonus();

    @Override
    public int getId() {
        return  1216;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShieldTransportCpuNeedBonus";
    }
}
