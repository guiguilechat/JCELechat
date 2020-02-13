package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerGallente2
    extends DoubleAttribute
{
    public static final ShipBonusTacticalDestroyerGallente2 INSTANCE = new ShipBonusTacticalDestroyerGallente2();

    @Override
    public int getId() {
        return  2028;
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
        return "ShipBonusTacticalDestroyerGallente2";
    }
}
