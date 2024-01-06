package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerGallente2
    extends RealAttribute
{
    public static final ShipBonusNavyDestroyerGallente2 INSTANCE = new ShipBonusNavyDestroyerGallente2();

    @Override
    public int getId() {
        return  5232;
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
        return "ShipBonusNavyDestroyerGallente2";
    }
}
