package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusNavyDestroyerCaldari4
    extends IntAttribute
{
    public static final ShipBonusNavyDestroyerCaldari4 INSTANCE = new ShipBonusNavyDestroyerCaldari4();

    @Override
    public int getId() {
        return  5227;
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
        return "ShipBonusNavyDestroyerCaldari4";
    }
}
