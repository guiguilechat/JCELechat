package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * shipBonusORECapital1
 */
public class ShipBonusORECapital1
    extends IntAttribute
{
    public static final ShipBonusORECapital1 INSTANCE = new ShipBonusORECapital1();

    @Override
    public int getId() {
        return  1239;
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
        return "ShipBonusORECapital1";
    }
}
