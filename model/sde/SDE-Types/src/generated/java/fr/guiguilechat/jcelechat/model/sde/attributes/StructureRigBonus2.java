package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * High-sec bonus on structure rigs.
 */
public class StructureRigBonus2
    extends RealAttribute
{
    public static final StructureRigBonus2 INSTANCE = new StructureRigBonus2();

    @Override
    public int getId() {
        return  2096;
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
