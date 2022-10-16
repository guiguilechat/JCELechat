package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class EntityShieldBoostDelayChanceLarge
    extends RealAttribute
{
    public static final EntityShieldBoostDelayChanceLarge INSTANCE = new EntityShieldBoostDelayChanceLarge();

    @Override
    public int getId() {
        return  1008;
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
        return "EntityShieldBoostDelayChanceLarge";
    }
}
