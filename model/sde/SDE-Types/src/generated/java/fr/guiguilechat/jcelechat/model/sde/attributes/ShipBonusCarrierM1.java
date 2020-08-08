package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Minmatar Carrier skill level.
 */
public class ShipBonusCarrierM1
    extends IntAttribute
{
    public static final ShipBonusCarrierM1 INSTANCE = new ShipBonusCarrierM1();

    @Override
    public int getId() {
        return  2371;
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
        return "ShipBonusCarrierM1";
    }
}
