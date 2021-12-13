package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipRoleBonusDroneHitPoints
    extends IntAttribute
{
    public static final ShipRoleBonusDroneHitPoints INSTANCE = new ShipRoleBonusDroneHitPoints();

    @Override
    public int getId() {
        return  3180;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ShipRoleBonusDroneHitPoints";
    }
}
