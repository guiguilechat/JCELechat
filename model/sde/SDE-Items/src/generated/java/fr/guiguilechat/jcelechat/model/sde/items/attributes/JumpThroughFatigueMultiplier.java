package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multiplier for jump fatigue distance, applied to characters going through a bridge provided by this type.
 */
public class JumpThroughFatigueMultiplier
    extends DoubleAttribute
{
    public static final JumpThroughFatigueMultiplier INSTANCE = new JumpThroughFatigueMultiplier();

    @Override
    public int getId() {
        return  1972;
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
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "JumpThroughFatigueMultiplier";
    }
}
