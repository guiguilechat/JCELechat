package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class StructureRigMissileExplosionRadiusBonus
    extends IntAttribute
{
    public static final StructureRigMissileExplosionRadiusBonus INSTANCE = new StructureRigMissileExplosionRadiusBonus();

    @Override
    public int getId() {
        return  2449;
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
        return "StructureRigMissileExplosionRadiusBonus";
    }
}
