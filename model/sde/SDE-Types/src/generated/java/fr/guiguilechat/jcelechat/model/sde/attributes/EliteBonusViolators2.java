package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * 
 */
public class EliteBonusViolators2
    extends RealAttribute
{
    public static final EliteBonusViolators2 INSTANCE = new EliteBonusViolators2();

    @Override
    public int getId() {
        return  1266;
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
        return "EliteBonusViolators2";
    }
}
