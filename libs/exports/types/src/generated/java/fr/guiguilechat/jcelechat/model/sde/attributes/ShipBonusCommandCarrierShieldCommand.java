package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusCommandCarrierShieldCommand
    extends IntAttribute
{
    public static final ShipBonusCommandCarrierShieldCommand INSTANCE = new ShipBonusCommandCarrierShieldCommand();

    @Override
    public int getId() {
        return  6195;
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
        return "ShipBonusCommandCarrierShieldCommand";
    }
}
