package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Impedance against Remote Repair (shield, armor, hull and energy).
 */
public class RemoteRepairImpedance
    extends RealAttribute
{
    public static final RemoteRepairImpedance INSTANCE = new RemoteRepairImpedance();

    @Override
    public int getId() {
        return  2116;
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
        return "RemoteRepairImpedance";
    }
}
