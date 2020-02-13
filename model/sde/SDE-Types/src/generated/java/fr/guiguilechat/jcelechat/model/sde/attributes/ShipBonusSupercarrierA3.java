package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Amarr Carrier skill level.
 */
public class ShipBonusSupercarrierA3
    extends IntAttribute
{
    public static final ShipBonusSupercarrierA3 INSTANCE = new ShipBonusSupercarrierA3();

    @Override
    public int getId() {
        return  2377;
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
        return "ShipBonusSupercarrierA3";
    }
}
