package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class StructureRigMissileExplosionRadiusBonus
    extends IntAttribute
{
    public final static StructureRigMissileExplosionRadiusBonus INSTANCE = new StructureRigMissileExplosionRadiusBonus();

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
}
