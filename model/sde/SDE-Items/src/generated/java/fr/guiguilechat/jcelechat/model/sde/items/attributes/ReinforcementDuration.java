package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The number of seconds that the structure will be in reinforcement time
 */
public class ReinforcementDuration
    extends IntAttribute
{
    public final static ReinforcementDuration INSTANCE = new ReinforcementDuration();

    @Override
    public int getId() {
        return  1612;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
