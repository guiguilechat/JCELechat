package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Warp Scramble Strength Bonus
 */
public class WarpScrambleStrengthBonus
    extends IntAttribute
{
    public static final WarpScrambleStrengthBonus INSTANCE = new WarpScrambleStrengthBonus();

    @Override
    public int getId() {
        return  3035;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "WarpScrambleStrengthBonus";
    }
}
