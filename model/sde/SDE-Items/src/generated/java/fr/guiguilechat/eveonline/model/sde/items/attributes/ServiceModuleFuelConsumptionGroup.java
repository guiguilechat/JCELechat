package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Fuel consumed by the structure service module
 */
public class ServiceModuleFuelConsumptionGroup
    extends IntAttribute
{
    public final static ServiceModuleFuelConsumptionGroup INSTANCE = new ServiceModuleFuelConsumptionGroup();

    @Override
    public int getId() {
        return  2108;
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
        return "ServiceModuleFuelConsumptionGroup";
    }
}
