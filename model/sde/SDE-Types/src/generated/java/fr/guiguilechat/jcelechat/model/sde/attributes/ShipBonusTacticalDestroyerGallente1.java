package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerGallente1
    extends IntAttribute
{
    public static final ShipBonusTacticalDestroyerGallente1 INSTANCE = new ShipBonusTacticalDestroyerGallente1();

    @Override
    public int getId() {
        return  2027;
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
        return "ShipBonusTacticalDestroyerGallente1";
    }
}
