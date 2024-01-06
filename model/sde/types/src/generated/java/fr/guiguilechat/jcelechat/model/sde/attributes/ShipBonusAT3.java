package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Ship Bonus used for Alliance Tournament Ships
 */
public class ShipBonusAT3
    extends IntAttribute
{
    public static final ShipBonusAT3 INSTANCE = new ShipBonusAT3();

    @Override
    public int getId() {
        return  5319;
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
        return "ShipBonusAT3";
    }
}
