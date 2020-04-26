package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


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
