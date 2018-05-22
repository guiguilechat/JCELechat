package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * High-sec bonus on structure rigs.
 */
public class StructureRigBonus2
    extends DoubleAttribute
{
    public final static StructureRigBonus2 INSTANCE = new StructureRigBonus2();

    @Override
    public int getId() {
        return  2096;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "StructureRigBonus2";
    }
}
