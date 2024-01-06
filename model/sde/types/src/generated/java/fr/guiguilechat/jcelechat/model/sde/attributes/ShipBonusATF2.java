package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Alliance Tournament Frigate Bonus
 */
public class ShipBonusATF2
    extends IntAttribute
{
    public static final ShipBonusATF2 INSTANCE = new ShipBonusATF2();

    @Override
    public int getId() {
        return  1577;
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
        return "ShipBonusATF2";
    }
}
