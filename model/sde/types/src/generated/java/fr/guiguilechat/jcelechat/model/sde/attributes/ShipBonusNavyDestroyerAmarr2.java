package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerAmarr2
    extends RealAttribute
{
    public static final ShipBonusNavyDestroyerAmarr2 INSTANCE = new ShipBonusNavyDestroyerAmarr2();

    @Override
    public int getId() {
        return  5219;
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
        return "ShipBonusNavyDestroyerAmarr2";
    }
}
