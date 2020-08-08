package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ORE Mining Barge bonus 3
 */
public class ShipBonusORE3
    extends IntAttribute
{
    public static final ShipBonusORE3 INSTANCE = new ShipBonusORE3();

    @Override
    public int getId() {
        return  926;
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
        return "ShipBonusORE3";
    }
}
