package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusSupercarrierC2
    extends IntAttribute
{
    public static final ShipBonusSupercarrierC2 INSTANCE = new ShipBonusSupercarrierC2();

    @Override
    public int getId() {
        return  2381;
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
        return "ShipBonusSupercarrierC2";
    }
}
