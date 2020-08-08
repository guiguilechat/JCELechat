package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EliteBonusCovertOps3
    extends DoubleAttribute
{
    public static final EliteBonusCovertOps3 INSTANCE = new EliteBonusCovertOps3();

    @Override
    public int getId() {
        return  1578;
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
        return "EliteBonusCovertOps3";
    }
}
