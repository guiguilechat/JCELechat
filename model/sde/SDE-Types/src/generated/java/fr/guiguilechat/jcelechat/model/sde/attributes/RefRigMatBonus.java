package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Refinery rig material bonus
 */
public class RefRigMatBonus
    extends DoubleAttribute
{
    public static final RefRigMatBonus INSTANCE = new RefRigMatBonus();

    @Override
    public int getId() {
        return  2714;
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
        return "RefRigMatBonus";
    }
}
