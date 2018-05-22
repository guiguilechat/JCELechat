package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerMinmatar1
    extends IntAttribute
{
    public final static ShipBonusTacticalDestroyerMinmatar1 INSTANCE = new ShipBonusTacticalDestroyerMinmatar1();

    @Override
    public int getId() {
        return  2004;
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
        return "ShipBonusTacticalDestroyerMinmatar1";
    }
}
