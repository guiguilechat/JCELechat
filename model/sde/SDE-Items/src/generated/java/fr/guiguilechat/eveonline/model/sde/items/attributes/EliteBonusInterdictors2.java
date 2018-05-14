package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * eliteBonusInterdictors2
 */
public class EliteBonusInterdictors2
    extends IntAttribute
{
    public final static EliteBonusInterdictors2 INSTANCE = new EliteBonusInterdictors2();

    @Override
    public int getId() {
        return  1013;
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
