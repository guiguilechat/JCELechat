package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Impedance against Remote Repair (shield, armor, hull and energy).
 */
public class RemoteRepairImpedance
    extends DoubleAttribute
{
    public final static RemoteRepairImpedance INSTANCE = new RemoteRepairImpedance();

    @Override
    public int getId() {
        return  2116;
    }

    @Override
    public int getCatId() {
        return  36;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
