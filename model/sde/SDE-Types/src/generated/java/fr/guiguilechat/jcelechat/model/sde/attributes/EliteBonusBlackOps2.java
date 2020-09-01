package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * eliteBonusBlackOps2
 */
public class EliteBonusBlackOps2
    extends RealAttribute
{
    public static final EliteBonusBlackOps2 INSTANCE = new EliteBonusBlackOps2();

    @Override
    public int getId() {
        return  1258;
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
        return "EliteBonusBlackOps2";
    }
}
