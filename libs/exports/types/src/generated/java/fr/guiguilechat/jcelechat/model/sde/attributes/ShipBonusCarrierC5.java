package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusCarrierC5
    extends IntAttribute
{
    public static final ShipBonusCarrierC5 INSTANCE = new ShipBonusCarrierC5();

    @Override
    public int getId() {
        return  5982;
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
        return "ShipBonusCarrierC5";
    }
}
