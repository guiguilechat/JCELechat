package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Refinery rig material bonus
 */
public class RefRigMatBonus
    extends DoubleAttribute
{
    public final static RefRigMatBonus INSTANCE = new RefRigMatBonus();

    @Override
    public int getId() {
        return  2714;
    }

    @Override
    public int getCatId() {
        return  37;
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
}
