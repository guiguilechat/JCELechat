package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The capacity of the hangar in a ship.
 */
public class ShipMaintenanceBayCapacity
    extends IntAttribute
{
    public final static ShipMaintenanceBayCapacity INSTANCE = new ShipMaintenanceBayCapacity();

    @Override
    public int getId() {
        return  908;
    }

    @Override
    public int getCatId() {
        return  40;
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
}
