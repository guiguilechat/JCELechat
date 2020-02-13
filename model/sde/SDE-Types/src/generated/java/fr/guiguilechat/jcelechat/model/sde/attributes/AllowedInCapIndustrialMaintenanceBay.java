package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Tells if this type (ship) can be placed in the maintenance bay of a capital industrial ship.
 */
public class AllowedInCapIndustrialMaintenanceBay
    extends IntAttribute
{
    public static final AllowedInCapIndustrialMaintenanceBay INSTANCE = new AllowedInCapIndustrialMaintenanceBay();

    @Override
    public int getId() {
        return  1891;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "AllowedInCapIndustrialMaintenanceBay";
    }
}
