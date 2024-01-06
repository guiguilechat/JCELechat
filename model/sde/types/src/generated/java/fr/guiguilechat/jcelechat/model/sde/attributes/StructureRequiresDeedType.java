package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Describes which type of deed fits in this structure
 */
public class StructureRequiresDeedType
    extends IntAttribute
{
    public static final StructureRequiresDeedType INSTANCE = new StructureRequiresDeedType();

    @Override
    public int getId() {
        return  3101;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "StructureRequiresDeedType";
    }
}
