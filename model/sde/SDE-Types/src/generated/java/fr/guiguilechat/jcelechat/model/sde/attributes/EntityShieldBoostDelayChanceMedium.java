package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class EntityShieldBoostDelayChanceMedium
    extends RealAttribute
{
    public static final EntityShieldBoostDelayChanceMedium INSTANCE = new EntityShieldBoostDelayChanceMedium();

    @Override
    public int getId() {
        return  1007;
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
        return "EntityShieldBoostDelayChanceMedium";
    }
}
