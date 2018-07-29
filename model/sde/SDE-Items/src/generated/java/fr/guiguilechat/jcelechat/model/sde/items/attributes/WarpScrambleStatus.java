package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Warp ability of a ship.  If greater than zero than the ship cannot warp.
 */
public class WarpScrambleStatus
    extends IntAttribute
{
    public final static WarpScrambleStatus INSTANCE = new WarpScrambleStatus();

    @Override
    public int getId() {
        return  104;
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
