package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusSupercarrierC5
    extends IntAttribute
{
    public final static ShipBonusSupercarrierC5 INSTANCE = new ShipBonusSupercarrierC5();

    @Override
    public int getId() {
        return  2384;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "ShipBonusSupercarrierC5";
    }
}
