package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Multiplier for jump fatigue distance
 */
public class JumpFatigueMultiplier
    extends RealAttribute
{
    public static final JumpFatigueMultiplier INSTANCE = new JumpFatigueMultiplier();

    @Override
    public int getId() {
        return  1971;
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
        return "JumpFatigueMultiplier";
    }
}
