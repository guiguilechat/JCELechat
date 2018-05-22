package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * This attribute is authored on structure service modules and when the service module is online will be used to overwrite a hitpoint multiplier attribute on the structure.
 */
public class ServiceModuleFullPowerStateHitpointMultiplier
    extends DoubleAttribute
{
    public final static ServiceModuleFullPowerStateHitpointMultiplier INSTANCE = new ServiceModuleFullPowerStateHitpointMultiplier();

    @Override
    public int getId() {
        return  2744;
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
        return  1.0;
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
        return "ServiceModuleFullPowerStateHitpointMultiplier";
    }
}
