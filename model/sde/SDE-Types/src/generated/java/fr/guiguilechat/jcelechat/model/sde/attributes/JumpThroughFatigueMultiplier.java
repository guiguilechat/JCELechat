package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier for jump fatigue distance, applied to characters going through a bridge provided by this type.
 */
public class JumpThroughFatigueMultiplier
    extends RealAttribute
{
    public static final JumpThroughFatigueMultiplier INSTANCE = new JumpThroughFatigueMultiplier();

    @Override
    public int getId() {
        return  1972;
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
