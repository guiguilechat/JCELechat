package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Ship Bonus used for Alliance Tournament Ships
 */
public class ShipBonusAT2
    extends IntAttribute
{
    public static final ShipBonusAT2 INSTANCE = new ShipBonusAT2();

    @Override
    public int getId() {
        return  5318;
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
        return "ShipBonusAT2";
    }
}
