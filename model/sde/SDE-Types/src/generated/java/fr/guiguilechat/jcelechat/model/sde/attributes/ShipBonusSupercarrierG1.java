package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Gallente Carrier skill level.
 */
public class ShipBonusSupercarrierG1
    extends IntAttribute
{
    public static final ShipBonusSupercarrierG1 INSTANCE = new ShipBonusSupercarrierG1();

    @Override
    public int getId() {
        return  2385;
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
        return "ShipBonusSupercarrierG1";
    }
}
