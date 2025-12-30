package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
 */
public class GateScrambleStatus
    extends IntAttribute
{
    public static final GateScrambleStatus INSTANCE = new GateScrambleStatus();

    @Override
    public int getId() {
        return  1973;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
