package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
 */
public class OreBasicType
    extends IntAttribute
{
    public final static OreBasicType INSTANCE = new OreBasicType();

    @Override
    public int getId() {
        return  2711;
    }

    @Override
    public int getCatId() {
        return  35;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
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
