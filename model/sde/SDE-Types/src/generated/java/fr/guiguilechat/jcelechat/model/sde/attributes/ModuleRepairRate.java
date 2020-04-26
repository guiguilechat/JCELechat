package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * dictates how many hitpoints you can repair per minute
 */
public class ModuleRepairRate
    extends IntAttribute
{
    public static final ModuleRepairRate INSTANCE = new ModuleRepairRate();

    @Override
    public int getId() {
        return  1267;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10.0;
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
        return "ModuleRepairRate";
    }
}
