package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
    public int getCatId() {
        return  27;
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
