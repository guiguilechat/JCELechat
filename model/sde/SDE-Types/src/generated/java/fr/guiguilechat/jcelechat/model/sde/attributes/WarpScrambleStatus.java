package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Warp ability of a ship.  If greater than zero than the ship cannot warp.
 */
public class WarpScrambleStatus
    extends IntAttribute
{
    public static final WarpScrambleStatus INSTANCE = new WarpScrambleStatus();

    @Override
    public int getId() {
        return  104;
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
        return "WarpScrambleStatus";
    }
}
