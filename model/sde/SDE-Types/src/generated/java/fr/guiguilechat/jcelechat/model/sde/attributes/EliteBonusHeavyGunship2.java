package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EliteBonusHeavyGunship2
    extends DoubleAttribute
{
    public static final EliteBonusHeavyGunship2 INSTANCE = new EliteBonusHeavyGunship2();

    @Override
    public int getId() {
        return  693;
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
        return "EliteBonusHeavyGunship2";
    }
}
