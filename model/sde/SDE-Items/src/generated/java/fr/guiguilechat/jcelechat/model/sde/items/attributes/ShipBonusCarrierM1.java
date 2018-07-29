package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Minmatar Carrier skill level.
 */
public class ShipBonusCarrierM1
    extends IntAttribute
{
    public final static ShipBonusCarrierM1 INSTANCE = new ShipBonusCarrierM1();

    @Override
    public int getId() {
        return  2371;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "ShipBonusCarrierM1";
    }
}
