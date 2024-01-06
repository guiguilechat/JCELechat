package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Alliance Tournament Ship Bonus
 */
public class ShipBonusATC1
    extends IntAttribute
{
    public static final ShipBonusATC1 INSTANCE = new ShipBonusATC1();

    @Override
    public int getId() {
        return  1574;
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
        return "ShipBonusATC1";
    }
}
