package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * shipBonusORECapital4
 */
public class ShipBonusORECapital4
    extends IntAttribute
{
    public static final ShipBonusORECapital4 INSTANCE = new ShipBonusORECapital4();

    @Override
    public int getId() {
        return  1244;
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
        return "ShipBonusORECapital4";
    }
}
