package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerAmarr6
    extends IntAttribute
{
    public static final ShipBonusNavyDestroyerAmarr6 INSTANCE = new ShipBonusNavyDestroyerAmarr6();

    @Override
    public int getId() {
        return  5223;
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
        return "ShipBonusNavyDestroyerAmarr6";
    }
}
