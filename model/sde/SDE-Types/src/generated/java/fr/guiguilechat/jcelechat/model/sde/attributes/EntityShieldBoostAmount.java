package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * How much the shield is boosted each duration.
 */
public class EntityShieldBoostAmount
    extends DoubleAttribute
{
    public static final EntityShieldBoostAmount INSTANCE = new EntityShieldBoostAmount();

    @Override
    public int getId() {
        return  637;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EntityShieldBoostAmount";
    }
}
