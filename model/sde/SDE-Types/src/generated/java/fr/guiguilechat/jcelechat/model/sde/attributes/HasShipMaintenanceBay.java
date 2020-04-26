package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Indicates whether a ship type has a ship maintenance bay.
 */
public class HasShipMaintenanceBay
    extends IntAttribute
{
    public static final HasShipMaintenanceBay INSTANCE = new HasShipMaintenanceBay();

    @Override
    public int getId() {
        return  907;
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
        return "HasShipMaintenanceBay";
    }
}
