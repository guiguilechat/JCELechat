package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Warp Scramble Range Bonus
 */
public class WarpScrambleRangeBonus
    extends IntAttribute
{
    public static final WarpScrambleRangeBonus INSTANCE = new WarpScrambleRangeBonus();

    @Override
    public int getId() {
        return  1327;
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
        return "WarpScrambleRangeBonus";
    }
}
