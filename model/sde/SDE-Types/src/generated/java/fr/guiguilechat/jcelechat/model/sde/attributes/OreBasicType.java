package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
 */
public class OreBasicType
    extends IntAttribute
{
    public static final OreBasicType INSTANCE = new OreBasicType();

    @Override
    public int getId() {
        return  2711;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
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
        return "OreBasicType";
    }
}
