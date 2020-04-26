package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * shipBonusORECapital3
 */
public class ShipBonusORECapital3
    extends IntAttribute
{
    public static final ShipBonusORECapital3 INSTANCE = new ShipBonusORECapital3();

    @Override
    public int getId() {
        return  1243;
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
        return "ShipBonusORECapital3";
    }
}
