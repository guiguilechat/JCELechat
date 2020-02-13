package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EliteBonusHeavyGunship1
    extends DoubleAttribute
{
    public static final EliteBonusHeavyGunship1 INSTANCE = new EliteBonusHeavyGunship1();

    @Override
    public int getId() {
        return  692;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "EliteBonusHeavyGunship1";
    }
}
