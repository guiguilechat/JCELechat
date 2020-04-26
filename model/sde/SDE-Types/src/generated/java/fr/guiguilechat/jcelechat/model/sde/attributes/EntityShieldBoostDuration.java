package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How long between repeats.
 */
public class EntityShieldBoostDuration
    extends IntAttribute
{
    public static final EntityShieldBoostDuration INSTANCE = new EntityShieldBoostDuration();

    @Override
    public int getId() {
        return  636;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityShieldBoostDuration";
    }
}
