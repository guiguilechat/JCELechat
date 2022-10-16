package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used to divide with mass to give a factor for speed boost modules
 */
public class SpeedBoostFactor
    extends IntAttribute
{
    public static final SpeedBoostFactor INSTANCE = new SpeedBoostFactor();

    @Override
    public int getId() {
        return  567;
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
        return "SpeedBoostFactor";
    }
}
