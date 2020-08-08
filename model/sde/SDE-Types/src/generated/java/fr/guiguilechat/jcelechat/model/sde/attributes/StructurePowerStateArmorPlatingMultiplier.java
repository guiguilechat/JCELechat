package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructurePowerStateArmorPlatingMultiplier
    extends IntAttribute
{
    public static final StructurePowerStateArmorPlatingMultiplier INSTANCE = new StructurePowerStateArmorPlatingMultiplier();

    @Override
    public int getId() {
        return  2805;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "StructurePowerStateArmorPlatingMultiplier";
    }
}
