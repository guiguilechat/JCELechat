package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusTacticalDestroyerCaldari2
    extends IntAttribute
{
    public static final ShipBonusTacticalDestroyerCaldari2 INSTANCE = new ShipBonusTacticalDestroyerCaldari2();

    @Override
    public int getId() {
        return  2016;
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
        return "ShipBonusTacticalDestroyerCaldari2";
    }
}
