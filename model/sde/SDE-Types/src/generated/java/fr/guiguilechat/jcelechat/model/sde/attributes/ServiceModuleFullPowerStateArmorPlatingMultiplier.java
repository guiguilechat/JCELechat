package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ServiceModuleFullPowerStateArmorPlatingMultiplier
    extends IntAttribute
{
    public static final ServiceModuleFullPowerStateArmorPlatingMultiplier INSTANCE = new ServiceModuleFullPowerStateArmorPlatingMultiplier();

    @Override
    public int getId() {
        return  2804;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ServiceModuleFullPowerStateArmorPlatingMultiplier";
    }
}
