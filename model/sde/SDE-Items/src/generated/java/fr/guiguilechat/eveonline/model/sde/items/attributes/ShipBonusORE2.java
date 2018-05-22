package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * ORE Mining barge bonus 2
 */
public class ShipBonusORE2
    extends IntAttribute
{
    public final static ShipBonusORE2 INSTANCE = new ShipBonusORE2();

    @Override
    public int getId() {
        return  774;
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
        return "ShipBonusORE2";
    }
}
