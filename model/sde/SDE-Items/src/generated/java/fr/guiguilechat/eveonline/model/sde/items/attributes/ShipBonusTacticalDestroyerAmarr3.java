package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerAmarr3
    extends IntAttribute
{
    public final static ShipBonusTacticalDestroyerAmarr3 INSTANCE = new ShipBonusTacticalDestroyerAmarr3();

    @Override
    public int getId() {
        return  1988;
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
        return "ShipBonusTacticalDestroyerAmarr3";
    }
}
