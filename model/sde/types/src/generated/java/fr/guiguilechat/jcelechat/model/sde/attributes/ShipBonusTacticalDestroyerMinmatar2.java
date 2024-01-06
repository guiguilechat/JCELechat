package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerMinmatar2
    extends IntAttribute
{
    public static final ShipBonusTacticalDestroyerMinmatar2 INSTANCE = new ShipBonusTacticalDestroyerMinmatar2();

    @Override
    public int getId() {
        return  2005;
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
        return "ShipBonusTacticalDestroyerMinmatar2";
    }
}
