package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerAmarr4
    extends IntAttribute
{
    public static final ShipBonusNavyDestroyerAmarr4 INSTANCE = new ShipBonusNavyDestroyerAmarr4();

    @Override
    public int getId() {
        return  5221;
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
        return "ShipBonusNavyDestroyerAmarr4";
    }
}
