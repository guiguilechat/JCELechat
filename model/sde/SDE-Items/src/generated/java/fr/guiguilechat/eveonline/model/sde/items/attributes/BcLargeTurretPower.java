package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Used by Battlecruisers for large turret powergrid reduction
 */
public class BcLargeTurretPower
    extends DoubleAttribute
{
    public final static BcLargeTurretPower INSTANCE = new BcLargeTurretPower();

    @Override
    public int getId() {
        return  1786;
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
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
