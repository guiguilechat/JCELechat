package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusCarrierA6
    extends IntAttribute
{
    public static final ShipBonusCarrierA6 INSTANCE = new ShipBonusCarrierA6();

    @Override
    public int getId() {
        return  6191;
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
        return "ShipBonusCarrierA6";
    }
}
