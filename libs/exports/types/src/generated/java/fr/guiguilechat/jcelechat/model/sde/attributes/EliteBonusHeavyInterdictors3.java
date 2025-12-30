package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusHeavyInterdictors3
 */
public class EliteBonusHeavyInterdictors3
    extends IntAttribute
{
    public static final EliteBonusHeavyInterdictors3 INSTANCE = new EliteBonusHeavyInterdictors3();

    @Override
    public int getId() {
        return  3250;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return false;
    }

    @Override
    public String toString() {
        return "EliteBonusHeavyInterdictors3";
    }
}
