package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusSupercarrierC5
    extends IntAttribute
{
    public static final ShipBonusSupercarrierC5 INSTANCE = new ShipBonusSupercarrierC5();

    @Override
    public int getId() {
        return  2384;
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
        return "ShipBonusSupercarrierC5";
    }
}
