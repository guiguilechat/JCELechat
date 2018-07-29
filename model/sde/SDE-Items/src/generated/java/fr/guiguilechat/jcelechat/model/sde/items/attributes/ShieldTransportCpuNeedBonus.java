package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Reduction of CPU need of shield transporters.
 */
public class ShieldTransportCpuNeedBonus
    extends IntAttribute
{
    public final static ShieldTransportCpuNeedBonus INSTANCE = new ShieldTransportCpuNeedBonus();

    @Override
    public int getId() {
        return  1216;
    }

    @Override
    public int getCatId() {
        return  2;
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
