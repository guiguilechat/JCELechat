package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
 */
public class GateScrambleStatus
    extends IntAttribute
{
    public final static GateScrambleStatus INSTANCE = new GateScrambleStatus();

    @Override
    public int getId() {
        return  1973;
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
        return -1000.0;
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
        return "GateScrambleStatus";
    }
}
