package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EliteBonusCovertOps2
    extends IntAttribute
{
    public static final EliteBonusCovertOps2 INSTANCE = new EliteBonusCovertOps2();

    @Override
    public int getId() {
        return  839;
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
        return "EliteBonusCovertOps2";
    }
}
