package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBrokenRepairCostMultiplierBonus
    extends IntAttribute
{
    public static final ShipBrokenRepairCostMultiplierBonus INSTANCE = new ShipBrokenRepairCostMultiplierBonus();

    @Override
    public int getId() {
        return  1294;
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
        return "ShipBrokenRepairCostMultiplierBonus";
    }
}
