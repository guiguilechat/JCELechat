package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * shipBonusORECapital2
 */
public class ShipBonusORECapital2
    extends IntAttribute
{
    public static final ShipBonusORECapital2 INSTANCE = new ShipBonusORECapital2();

    @Override
    public int getId() {
        return  1240;
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
        return "ShipBonusORECapital2";
    }
}
