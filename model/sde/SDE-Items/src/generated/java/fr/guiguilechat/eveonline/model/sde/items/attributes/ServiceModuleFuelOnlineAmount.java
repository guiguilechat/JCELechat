package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Fuel consumed to online the service module.
 */
public class ServiceModuleFuelOnlineAmount
    extends IntAttribute
{
    public final static ServiceModuleFuelOnlineAmount INSTANCE = new ServiceModuleFuelOnlineAmount();

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
}
