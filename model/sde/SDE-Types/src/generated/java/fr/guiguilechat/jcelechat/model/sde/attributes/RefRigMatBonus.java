package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Refinery rig material bonus
 */
public class RefRigMatBonus
    extends RealAttribute
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
    public Number getDefaultValue() {
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
