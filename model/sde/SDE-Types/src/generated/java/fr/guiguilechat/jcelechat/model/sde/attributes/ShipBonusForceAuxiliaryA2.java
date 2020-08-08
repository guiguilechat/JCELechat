package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Amarr Carrier skill level.
 */
public class ShipBonusForceAuxiliaryA2
    extends IntAttribute
{
    public static final ShipBonusForceAuxiliaryA2 INSTANCE = new ShipBonusForceAuxiliaryA2();

    @Override
    public int getId() {
        return  2309;
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
        return "ShipBonusForceAuxiliaryA2";
    }
}
