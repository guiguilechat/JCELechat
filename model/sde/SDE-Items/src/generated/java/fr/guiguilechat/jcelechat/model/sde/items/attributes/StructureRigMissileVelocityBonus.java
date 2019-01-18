package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class StructureRigMissileVelocityBonus
    extends IntAttribute
{
    public static final StructureRigMissileVelocityBonus INSTANCE = new StructureRigMissileVelocityBonus();

    @Override
    public int getId() {
        return  2439;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "StructureRigMissileVelocityBonus";
    }
}
