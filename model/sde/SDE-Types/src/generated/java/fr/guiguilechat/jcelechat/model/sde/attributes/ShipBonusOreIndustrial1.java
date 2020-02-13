package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus 1 for ORE Industrials
 */
public class ShipBonusOreIndustrial1
    extends IntAttribute
{
    public static final ShipBonusOreIndustrial1 INSTANCE = new ShipBonusOreIndustrial1();

    @Override
    public int getId() {
        return  1669;
    }

    @Override
    public int getCatId() {
        return  0;
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
        return "ShipBonusOreIndustrial1";
    }
}
