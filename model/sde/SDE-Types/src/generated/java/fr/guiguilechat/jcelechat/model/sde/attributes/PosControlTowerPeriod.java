package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The interval for fuel consumption of a control tower
 */
public class PosControlTowerPeriod
    extends IntAttribute
{
    public static final PosControlTowerPeriod INSTANCE = new PosControlTowerPeriod();

    @Override
    public int getId() {
        return  722;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  10000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PosControlTowerPeriod";
    }
}
