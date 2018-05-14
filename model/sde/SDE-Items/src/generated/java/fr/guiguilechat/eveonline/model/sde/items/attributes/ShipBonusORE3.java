package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * ORE Mining Barge bonus 3
 */
public class ShipBonusORE3
    extends IntAttribute
{
    public final static ShipBonusORE3 INSTANCE = new ShipBonusORE3();

    @Override
    public int getId() {
        return  926;
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
}
