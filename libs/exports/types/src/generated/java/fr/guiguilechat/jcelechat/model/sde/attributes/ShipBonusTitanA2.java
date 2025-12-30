package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Amarr Titan skill level.
 */
public class ShipBonusTitanA2
    extends IntAttribute
{
    public static final ShipBonusTitanA2 INSTANCE = new ShipBonusTitanA2();

    @Override
    public int getId() {
        return  2407;
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
        return "ShipBonusTitanA2";
    }
}
