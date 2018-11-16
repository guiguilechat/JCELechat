package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class RepairMultiplierBonusPerCycle
    extends IntAttribute
{
    public final static RepairMultiplierBonusPerCycle INSTANCE = new RepairMultiplierBonusPerCycle();

    @Override
    public int getId() {
        return  2796;
    }

    @Override
    public int getCatId() {
        return  20;
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
