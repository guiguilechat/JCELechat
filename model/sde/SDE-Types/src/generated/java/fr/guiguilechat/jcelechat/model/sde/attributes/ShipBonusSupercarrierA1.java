package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplied by Amarr Carrier skill level.
 */
public class ShipBonusSupercarrierA1
    extends RealAttribute
{
    public static final ShipBonusSupercarrierA1 INSTANCE = new ShipBonusSupercarrierA1();

    @Override
    public int getId() {
        return  2375;
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
        return "ShipBonusSupercarrierA1";
    }
}
