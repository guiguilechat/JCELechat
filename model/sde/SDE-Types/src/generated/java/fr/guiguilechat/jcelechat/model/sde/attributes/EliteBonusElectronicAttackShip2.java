package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusElectronicAttackShip2
 */
public class EliteBonusElectronicAttackShip2
    extends IntAttribute
{
    public static final EliteBonusElectronicAttackShip2 INSTANCE = new EliteBonusElectronicAttackShip2();

    @Override
    public int getId() {
        return  1250;
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
        return "EliteBonusElectronicAttackShip2";
    }
}
