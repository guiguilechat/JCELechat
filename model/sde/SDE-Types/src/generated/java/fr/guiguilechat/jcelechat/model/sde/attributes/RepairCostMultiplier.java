package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Multiplier to adjust the cost of repairs.
 */
public class RepairCostMultiplier
    extends DoubleAttribute
{
    public static final RepairCostMultiplier INSTANCE = new RepairCostMultiplier();

    @Override
    public int getId() {
        return  187;
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
