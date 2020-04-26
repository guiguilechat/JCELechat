package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class RepairMultiplierBonusPerCycle
    extends DoubleAttribute
{
    public static final RepairMultiplierBonusPerCycle INSTANCE = new RepairMultiplierBonusPerCycle();

    @Override
    public int getId() {
        return  2796;
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
        return "RepairMultiplierBonusPerCycle";
    }
}
