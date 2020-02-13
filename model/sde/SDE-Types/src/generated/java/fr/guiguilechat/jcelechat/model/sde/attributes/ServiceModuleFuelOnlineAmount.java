package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Fuel consumed to online the service module.
 */
public class ServiceModuleFuelOnlineAmount
    extends IntAttribute
{
    public static final ServiceModuleFuelOnlineAmount INSTANCE = new ServiceModuleFuelOnlineAmount();

    @Override
    public int getId() {
        return  2110;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "ServiceModuleFuelOnlineAmount";
    }
}
