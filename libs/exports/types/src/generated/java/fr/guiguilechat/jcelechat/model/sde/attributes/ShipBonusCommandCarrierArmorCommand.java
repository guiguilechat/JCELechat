package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusCommandCarrierArmorCommand
    extends IntAttribute
{
    public static final ShipBonusCommandCarrierArmorCommand INSTANCE = new ShipBonusCommandCarrierArmorCommand();

    @Override
    public int getId() {
        return  6192;
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
        return "ShipBonusCommandCarrierArmorCommand";
    }
}
