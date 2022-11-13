package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerAmarr1
    extends RealAttribute
{
    public static final ShipBonusNavyDestroyerAmarr1 INSTANCE = new ShipBonusNavyDestroyerAmarr1();

    @Override
    public int getId() {
        return  5218;
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
        return "ShipBonusNavyDestroyerAmarr1";
    }
}
