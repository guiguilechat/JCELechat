package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Amarr Titan skill level.
 */
public class ShipBonusTitanA3
    extends IntAttribute
{
    public static final ShipBonusTitanA3 INSTANCE = new ShipBonusTitanA3();

    @Override
    public int getId() {
        return  2408;
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
        return "ShipBonusTitanA3";
    }
}
