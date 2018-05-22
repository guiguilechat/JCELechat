package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerMinmatar3
    extends IntAttribute
{
    public final static ShipBonusTacticalDestroyerMinmatar3 INSTANCE = new ShipBonusTacticalDestroyerMinmatar3();

    @Override
    public int getId() {
        return  2006;
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
        return "ShipBonusTacticalDestroyerMinmatar3";
    }
}
