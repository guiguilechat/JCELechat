package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Alliance Tournament Ship Bonus
 */
public class ShipBonusATC2
    extends IntAttribute
{
    public static final ShipBonusATC2 INSTANCE = new ShipBonusATC2();

    @Override
    public int getId() {
        return  1575;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "ShipBonusATC2";
    }
}
