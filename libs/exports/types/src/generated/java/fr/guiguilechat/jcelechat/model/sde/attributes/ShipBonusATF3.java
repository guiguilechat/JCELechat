package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * used for alliance tournament ships 2023, plate mass reduction
 */
public class ShipBonusATF3
    extends IntAttribute
{
    public static final ShipBonusATF3 INSTANCE = new ShipBonusATF3();

    @Override
    public int getId() {
        return  5603;
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
        return "ShipBonusATF3";
    }
}
