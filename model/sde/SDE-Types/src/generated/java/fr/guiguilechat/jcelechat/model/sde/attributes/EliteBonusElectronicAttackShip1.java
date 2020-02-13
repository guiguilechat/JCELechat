package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusElectronicAttackShip1
 */
public class EliteBonusElectronicAttackShip1
    extends IntAttribute
{
    public static final EliteBonusElectronicAttackShip1 INSTANCE = new EliteBonusElectronicAttackShip1();

    @Override
    public int getId() {
        return  1249;
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
        return "EliteBonusElectronicAttackShip1";
    }
}
