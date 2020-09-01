package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ShipBrokenModuleRepairCostMultiplier
    extends RealAttribute
{
    public static final ShipBrokenModuleRepairCostMultiplier INSTANCE = new ShipBrokenModuleRepairCostMultiplier();

    @Override
    public int getId() {
        return  1277;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.5;
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
        return "ShipBrokenModuleRepairCostMultiplier";
    }
}
