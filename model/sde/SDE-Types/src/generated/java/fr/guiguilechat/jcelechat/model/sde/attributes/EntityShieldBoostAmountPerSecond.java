package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * the average shield amount regenerated per second
 */
public class EntityShieldBoostAmountPerSecond
    extends DoubleAttribute
{
    public static final EntityShieldBoostAmountPerSecond INSTANCE = new EntityShieldBoostAmountPerSecond();

    @Override
    public int getId() {
        return  1893;
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
        return "EntityShieldBoostAmountPerSecond";
    }
}
