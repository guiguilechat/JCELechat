package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class CapitalIndustrialShipBonusDroneIceCycleTime
    extends IntAttribute
{
    public static final CapitalIndustrialShipBonusDroneIceCycleTime INSTANCE = new CapitalIndustrialShipBonusDroneIceCycleTime();

    @Override
    public int getId() {
        return  3224;
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
        return "CapitalIndustrialShipBonusDroneIceCycleTime";
    }
}
