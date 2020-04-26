package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureRigPDCapUseBonus
    extends IntAttribute
{
    public static final StructureRigPDCapUseBonus INSTANCE = new StructureRigPDCapUseBonus();

    @Override
    public int getId() {
        return  2437;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StructureRigPDCapUseBonus";
    }
}
