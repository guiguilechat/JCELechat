package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Maximum velocity multiplier
 */
public class MaxVelocityMultiplier
    extends IntAttribute
{
    public final static MaxVelocityMultiplier INSTANCE = new MaxVelocityMultiplier();

    @Override
    public int getId() {
        return  1470;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "MaxVelocityMultiplier";
    }
}
