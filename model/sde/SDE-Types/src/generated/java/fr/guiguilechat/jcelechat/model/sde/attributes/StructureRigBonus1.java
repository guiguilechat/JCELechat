package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * High-sec bonus on structure rigs.
 */
public class StructureRigBonus1
    extends IntAttribute
{
    public static final StructureRigBonus1 INSTANCE = new StructureRigBonus1();

    @Override
    public int getId() {
        return  2095;
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
        return "StructureRigBonus1";
    }
}
