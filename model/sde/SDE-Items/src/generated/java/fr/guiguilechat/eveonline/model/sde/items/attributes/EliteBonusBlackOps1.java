package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * eliteBonusBlackOps1
 */
public class EliteBonusBlackOps1
    extends DoubleAttribute
{
    public final static EliteBonusBlackOps1 INSTANCE = new EliteBonusBlackOps1();

    @Override
    public int getId() {
        return  1257;
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
