package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of seconds that the reinforcement exit time will be adjusted by. exitTime +- attribute
 */
public class ReinforcementVariance
    extends IntAttribute
{
    public static final ReinforcementVariance INSTANCE = new ReinforcementVariance();

    @Override
    public int getId() {
        return  1613;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  10800.0;
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
        return "ReinforcementVariance";
    }
}
