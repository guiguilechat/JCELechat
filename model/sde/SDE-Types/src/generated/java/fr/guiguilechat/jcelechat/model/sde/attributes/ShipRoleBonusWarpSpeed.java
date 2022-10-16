package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipRoleBonusWarpSpeed
    extends IntAttribute
{
    public static final ShipRoleBonusWarpSpeed INSTANCE = new ShipRoleBonusWarpSpeed();

    @Override
    public int getId() {
        return  2789;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "ShipRoleBonusWarpSpeed";
    }
}
