package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Alliance Tournament Frigate Bonus
 */
public class ShipBonusATF1
    extends IntAttribute
{
    public static final ShipBonusATF1 INSTANCE = new ShipBonusATF1();

    @Override
    public int getId() {
        return  1576;
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
        return "ShipBonusATF1";
    }
}
