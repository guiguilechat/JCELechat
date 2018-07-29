package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * shipBonusORECapital4
 */
public class ShipBonusORECapital4
    extends IntAttribute
{
    public final static ShipBonusORECapital4 INSTANCE = new ShipBonusORECapital4();

    @Override
    public int getId() {
        return  1244;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "ShipBonusORECapital4";
    }
}
