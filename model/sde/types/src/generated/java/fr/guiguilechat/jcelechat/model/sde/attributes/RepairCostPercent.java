package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Repair cost percent
 */
public class RepairCostPercent
    extends IntAttribute
{
    public static final RepairCostPercent INSTANCE = new RepairCostPercent();

    @Override
    public int getId() {
        return  396;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  100.0;
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
        return "RepairCostPercent";
    }
}
