package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class HeavyDroneDamagePercent
    extends IntAttribute
{
    public static final HeavyDroneDamagePercent INSTANCE = new HeavyDroneDamagePercent();

    @Override
    public int getId() {
        return  426;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "HeavyDroneDamagePercent";
    }
}
