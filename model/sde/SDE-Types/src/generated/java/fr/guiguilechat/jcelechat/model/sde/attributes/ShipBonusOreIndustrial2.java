package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus 2 for ORE Industrials
 */
public class ShipBonusOreIndustrial2
    extends IntAttribute
{
    public static final ShipBonusOreIndustrial2 INSTANCE = new ShipBonusOreIndustrial2();

    @Override
    public int getId() {
        return  1670;
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
        return "ShipBonusOreIndustrial2";
    }
}
