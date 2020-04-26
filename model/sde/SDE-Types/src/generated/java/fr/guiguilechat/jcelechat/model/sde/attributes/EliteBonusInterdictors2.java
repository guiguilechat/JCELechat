package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusInterdictors2
 */
public class EliteBonusInterdictors2
    extends IntAttribute
{
    public static final EliteBonusInterdictors2 INSTANCE = new EliteBonusInterdictors2();

    @Override
    public int getId() {
        return  1013;
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
        return "EliteBonusInterdictors2";
    }
}
