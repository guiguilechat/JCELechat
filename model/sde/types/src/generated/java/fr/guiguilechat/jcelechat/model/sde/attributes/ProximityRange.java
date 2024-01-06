package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The distance at which to react when relevant objects come within range.
 */
public class ProximityRange
    extends IntAttribute
{
    public static final ProximityRange INSTANCE = new ProximityRange();

    @Override
    public int getId() {
        return  154;
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
        return "ProximityRange";
    }
}
