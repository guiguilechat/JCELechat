package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * eliteBonusElectronicAttackShip3
 */
public class EliteBonusElectronicAttackShip3
    extends IntAttribute
{
    public final static EliteBonusElectronicAttackShip3 INSTANCE = new EliteBonusElectronicAttackShip3();

    @Override
    public int getId() {
        return  2069;
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
