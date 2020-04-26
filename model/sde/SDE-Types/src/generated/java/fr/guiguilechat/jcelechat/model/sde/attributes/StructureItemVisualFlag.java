package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Dogma attribute that specifies if the item should have the structure icon or not.
 */
public class StructureItemVisualFlag
    extends IntAttribute
{
    public static final StructureItemVisualFlag INSTANCE = new StructureItemVisualFlag();

    @Override
    public int getId() {
        return  2334;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StructureItemVisualFlag";
    }
}
