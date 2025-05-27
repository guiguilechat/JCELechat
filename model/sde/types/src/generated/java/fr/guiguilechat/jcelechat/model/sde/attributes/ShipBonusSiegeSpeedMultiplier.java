package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class ShipBonusSiegeSpeedMultiplier
    extends IntAttribute
{
    public static final ShipBonusSiegeSpeedMultiplier INSTANCE = new ShipBonusSiegeSpeedMultiplier();

    @Override
    public int getId() {
        return  5767;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "ShipBonusSiegeSpeedMultiplier";
    }
}
