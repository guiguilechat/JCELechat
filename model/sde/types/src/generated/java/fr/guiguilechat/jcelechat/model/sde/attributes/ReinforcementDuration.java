package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of seconds that the structure will be in reinforcement time
 */
public class ReinforcementDuration
    extends IntAttribute
{
    public static final ReinforcementDuration INSTANCE = new ReinforcementDuration();

    @Override
    public int getId() {
        return  1612;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  172800.0;
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
        return "ReinforcementDuration";
    }
}
