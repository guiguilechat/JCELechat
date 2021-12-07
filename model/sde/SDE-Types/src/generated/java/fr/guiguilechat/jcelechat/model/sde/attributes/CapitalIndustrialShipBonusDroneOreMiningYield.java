package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


public class CapitalIndustrialShipBonusDroneOreMiningYield
    extends IntAttribute
{
    public static final CapitalIndustrialShipBonusDroneOreMiningYield INSTANCE = new CapitalIndustrialShipBonusDroneOreMiningYield();

    @Override
    public int getId() {
        return  3223;
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
        return "CapitalIndustrialShipBonusDroneOreMiningYield";
    }
}
