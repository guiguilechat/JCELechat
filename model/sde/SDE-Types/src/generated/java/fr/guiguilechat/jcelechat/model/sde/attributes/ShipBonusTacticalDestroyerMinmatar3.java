package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerMinmatar3
    extends IntAttribute
{
    public static final ShipBonusTacticalDestroyerMinmatar3 INSTANCE = new ShipBonusTacticalDestroyerMinmatar3();

    @Override
    public int getId() {
        return  2006;
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
