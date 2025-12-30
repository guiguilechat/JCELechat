package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusSupercarrierC1
    extends RealAttribute
{
    public static final ShipBonusSupercarrierC1 INSTANCE = new ShipBonusSupercarrierC1();

    @Override
    public int getId() {
        return  2380;
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
        return "ShipBonusSupercarrierC1";
    }
}
