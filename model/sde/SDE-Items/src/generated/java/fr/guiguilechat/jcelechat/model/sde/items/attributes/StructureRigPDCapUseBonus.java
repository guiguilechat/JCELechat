package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class StructureRigPDCapUseBonus
    extends IntAttribute
{
    public final static StructureRigPDCapUseBonus INSTANCE = new StructureRigPDCapUseBonus();

    @Override
    public int getId() {
        return  2437;
    }

    @Override
    public int getCatId() {
        return  7;
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