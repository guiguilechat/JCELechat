package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerAmarr5
    extends IntAttribute
{
    public static final ShipBonusNavyDestroyerAmarr5 INSTANCE = new ShipBonusNavyDestroyerAmarr5();

    @Override
    public int getId() {
        return  5222;
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
        return "ShipBonusNavyDestroyerAmarr5";
    }
}
