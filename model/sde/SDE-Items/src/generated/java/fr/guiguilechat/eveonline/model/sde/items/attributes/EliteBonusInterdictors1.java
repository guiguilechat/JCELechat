package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * eliteBonusInterdictors1
 */
public class EliteBonusInterdictors1
    extends IntAttribute
{
    public final static EliteBonusInterdictors1 INSTANCE = new EliteBonusInterdictors1();

    @Override
    public int getId() {
        return  1012;
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
}
