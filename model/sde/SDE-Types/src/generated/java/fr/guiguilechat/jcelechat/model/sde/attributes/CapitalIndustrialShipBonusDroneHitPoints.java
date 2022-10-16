package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class CapitalIndustrialShipBonusDroneHitPoints
    extends IntAttribute
{
    public static final CapitalIndustrialShipBonusDroneHitPoints INSTANCE = new CapitalIndustrialShipBonusDroneHitPoints();

    @Override
    public int getId() {
        return  3233;
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
        return "CapitalIndustrialShipBonusDroneHitPoints";
    }
}
