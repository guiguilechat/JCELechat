package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * 
 */
public class RemoteRepairImpedanceBonus
    extends DoubleAttribute
{
    public static final RemoteRepairImpedanceBonus INSTANCE = new RemoteRepairImpedanceBonus();

    @Override
    public int getId() {
        return  2342;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return true;
    }

    @Override
    public String toString() {
        return "RemoteRepairImpedanceBonus";
    }
}
