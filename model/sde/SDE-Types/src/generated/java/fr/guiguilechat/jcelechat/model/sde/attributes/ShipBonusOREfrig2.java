package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * ORE Mining frigate bonus 2
 */
public class ShipBonusOREfrig2
    extends IntAttribute
{
    public static final ShipBonusOREfrig2 INSTANCE = new ShipBonusOREfrig2();

    @Override
    public int getId() {
        return  1843;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "ShipBonusOREfrig2";
    }
}
