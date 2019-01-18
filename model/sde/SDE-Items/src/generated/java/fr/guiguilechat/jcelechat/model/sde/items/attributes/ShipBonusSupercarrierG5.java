package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusSupercarrierG5
    extends IntAttribute
{
    public static final ShipBonusSupercarrierG5 INSTANCE = new ShipBonusSupercarrierG5();

    @Override
    public int getId() {
        return  2389;
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
        return "ShipBonusSupercarrierG5";
    }
}
