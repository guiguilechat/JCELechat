package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ModuleRepairRateBonus
    extends IntAttribute
{
    public static final ModuleRepairRateBonus INSTANCE = new ModuleRepairRateBonus();

    @Override
    public int getId() {
        return  1295;
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
        return "ModuleRepairRateBonus";
    }
}
