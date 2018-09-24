package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier to adjust the cost of repairs.
 */
public class RepairCostMultiplier
    extends DoubleAttribute
{
    public final static RepairCostMultiplier INSTANCE = new RepairCostMultiplier();

    @Override
    public int getId() {
        return  187;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RepairCostMultiplier";
    }
}
