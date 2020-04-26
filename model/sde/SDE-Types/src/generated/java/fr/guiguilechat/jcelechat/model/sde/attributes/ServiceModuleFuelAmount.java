package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fuel consumed at the beginning of each hour to keep a service module online.
 */
public class ServiceModuleFuelAmount
    extends IntAttribute
{
    public static final ServiceModuleFuelAmount INSTANCE = new ServiceModuleFuelAmount();

    @Override
    public int getId() {
        return  2109;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "ServiceModuleFuelAmount";
    }
}
