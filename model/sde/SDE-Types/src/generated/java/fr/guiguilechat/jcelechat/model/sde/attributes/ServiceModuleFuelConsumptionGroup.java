package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fuel consumed by the structure service module
 */
public class ServiceModuleFuelConsumptionGroup
    extends IntAttribute
{
    public static final ServiceModuleFuelConsumptionGroup INSTANCE = new ServiceModuleFuelConsumptionGroup();

    @Override
    public int getId() {
        return  2108;
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
