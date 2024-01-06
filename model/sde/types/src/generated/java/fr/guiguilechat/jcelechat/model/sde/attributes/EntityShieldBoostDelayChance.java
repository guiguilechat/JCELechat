package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The chance an entity will delay repeating use of its shield boosting effect if it has one.
 */
public class EntityShieldBoostDelayChance
    extends RealAttribute
{
    public static final EntityShieldBoostDelayChance INSTANCE = new EntityShieldBoostDelayChance();

    @Override
    public int getId() {
        return  639;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EntityShieldBoostDelayChance";
    }
}
