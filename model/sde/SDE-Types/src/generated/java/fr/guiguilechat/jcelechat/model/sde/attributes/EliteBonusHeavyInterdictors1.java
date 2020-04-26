package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusHeavyInterdictors1
 */
public class EliteBonusHeavyInterdictors1
    extends IntAttribute
{
    public static final EliteBonusHeavyInterdictors1 INSTANCE = new EliteBonusHeavyInterdictors1();

    @Override
    public int getId() {
        return  1246;
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
        return "EliteBonusHeavyInterdictors1";
    }
}
