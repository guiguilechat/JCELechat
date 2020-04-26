package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * eliteBonusBlackOps1
 */
public class EliteBonusBlackOps1
    extends DoubleAttribute
{
    public static final EliteBonusBlackOps1 INSTANCE = new EliteBonusBlackOps1();

    @Override
    public int getId() {
        return  1257;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EliteBonusBlackOps1";
    }
}
