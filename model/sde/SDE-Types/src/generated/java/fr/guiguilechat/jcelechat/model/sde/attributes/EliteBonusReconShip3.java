package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EliteBonusReconShip3
    extends DoubleAttribute
{
    public static final EliteBonusReconShip3 INSTANCE = new EliteBonusReconShip3();

    @Override
    public int getId() {
        return  1537;
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
        return "EliteBonusReconShip3";
    }
}
