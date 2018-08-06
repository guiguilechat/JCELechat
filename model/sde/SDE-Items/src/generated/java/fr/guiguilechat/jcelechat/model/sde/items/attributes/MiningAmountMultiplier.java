package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The factor by which the amount mined by a mining laser is scaled.
 */
public class MiningAmountMultiplier
    extends IntAttribute
{
    public final static MiningAmountMultiplier INSTANCE = new MiningAmountMultiplier();

    @Override
    public int getId() {
        return  207;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "MiningAmountMultiplier";
    }
}
