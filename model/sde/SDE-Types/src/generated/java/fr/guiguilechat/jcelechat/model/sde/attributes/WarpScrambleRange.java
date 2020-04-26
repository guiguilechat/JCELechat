package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum range objects can be warp scrambled from.
 */
public class WarpScrambleRange
    extends IntAttribute
{
    public static final WarpScrambleRange INSTANCE = new WarpScrambleRange();

    @Override
    public int getId() {
        return  103;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "WarpScrambleRange";
    }
}
