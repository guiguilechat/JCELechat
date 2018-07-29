package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Multiplied by Amarr Carrier skill level.
 */
public class ShipBonusCarrierA2
    extends IntAttribute
{
    public final static ShipBonusCarrierA2 INSTANCE = new ShipBonusCarrierA2();

    @Override
    public int getId() {
        return  2360;
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
        return "ShipBonusCarrierA2";
    }
}
