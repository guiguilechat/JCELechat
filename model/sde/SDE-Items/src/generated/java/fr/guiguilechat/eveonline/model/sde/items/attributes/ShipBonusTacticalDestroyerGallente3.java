package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerGallente3
    extends IntAttribute
{
    public final static ShipBonusTacticalDestroyerGallente3 INSTANCE = new ShipBonusTacticalDestroyerGallente3();

    @Override
    public int getId() {
        return  2029;
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
}
