package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * High-sec bonus on structure rigs.
 */
public class StructureRigBonus1
    extends IntAttribute
{
    public final static StructureRigBonus1 INSTANCE = new StructureRigBonus1();

    @Override
    public int getId() {
        return  2095;
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
        return "StructureRigBonus1";
    }
}
