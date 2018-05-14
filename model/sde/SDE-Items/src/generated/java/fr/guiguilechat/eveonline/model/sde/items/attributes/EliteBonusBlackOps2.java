package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * eliteBonusBlackOps2
 */
public class EliteBonusBlackOps2
    extends DoubleAttribute
{
    public final static EliteBonusBlackOps2 INSTANCE = new EliteBonusBlackOps2();

    @Override
    public int getId() {
        return  1258;
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
}
