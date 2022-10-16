package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Megawatts per kilometer
 */
public class PowerLoadPerKm
    extends RealAttribute
{
    public static final PowerLoadPerKm INSTANCE = new PowerLoadPerKm();

    @Override
    public int getId() {
        return  1633;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "PowerLoadPerKm";
    }
}
