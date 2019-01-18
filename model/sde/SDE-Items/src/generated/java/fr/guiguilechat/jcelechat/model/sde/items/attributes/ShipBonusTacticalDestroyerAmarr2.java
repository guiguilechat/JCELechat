package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerAmarr2
    extends IntAttribute
{
    public static final ShipBonusTacticalDestroyerAmarr2 INSTANCE = new ShipBonusTacticalDestroyerAmarr2();

    @Override
    public int getId() {
        return  1987;
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
        return "ShipBonusTacticalDestroyerAmarr2";
    }
}
