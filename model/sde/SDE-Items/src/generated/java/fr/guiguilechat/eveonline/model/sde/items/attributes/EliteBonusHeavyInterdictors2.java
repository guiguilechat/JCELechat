package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * eliteBonusHeavyInterdictors2
 */
public class EliteBonusHeavyInterdictors2
    extends IntAttribute
{
    public final static EliteBonusHeavyInterdictors2 INSTANCE = new EliteBonusHeavyInterdictors2();

    @Override
    public int getId() {
        return  1247;
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
