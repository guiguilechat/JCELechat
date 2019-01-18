package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * For charges, hidden attribute used by sentry guns to modify target pick range.
 */
public class EntityFlyRangeMultiplier
    extends DoubleAttribute
{
    public static final EntityFlyRangeMultiplier INSTANCE = new EntityFlyRangeMultiplier();

    @Override
    public int getId() {
        return  779;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "EntityFlyRangeMultiplier";
    }
}
