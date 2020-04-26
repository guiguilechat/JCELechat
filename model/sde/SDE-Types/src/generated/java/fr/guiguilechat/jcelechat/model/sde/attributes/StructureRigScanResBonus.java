package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureRigScanResBonus
    extends IntAttribute
{
    public static final StructureRigScanResBonus INSTANCE = new StructureRigScanResBonus();

    @Override
    public int getId() {
        return  2435;
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
        return false;
    }

    @Override
    public String toString() {
        return "StructureRigScanResBonus";
    }
}
