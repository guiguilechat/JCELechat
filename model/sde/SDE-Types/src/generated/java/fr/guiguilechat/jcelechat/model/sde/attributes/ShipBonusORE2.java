package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ORE Mining barge bonus 2
 */
public class ShipBonusORE2
    extends IntAttribute
{
    public static final ShipBonusORE2 INSTANCE = new ShipBonusORE2();

    @Override
    public int getId() {
        return  774;
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
        return "ShipBonusORE2";
    }
}
