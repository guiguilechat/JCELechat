package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureRigPDRangeBonus
    extends IntAttribute
{
    public static final StructureRigPDRangeBonus INSTANCE = new StructureRigPDRangeBonus();

    @Override
    public int getId() {
        return  2436;
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
        return false;
    }

    @Override
    public String toString() {
        return "StructureRigPDRangeBonus";
    }
}
