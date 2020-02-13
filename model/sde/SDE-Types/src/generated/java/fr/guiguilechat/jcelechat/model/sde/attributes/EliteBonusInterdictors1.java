package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusInterdictors1
 */
public class EliteBonusInterdictors1
    extends IntAttribute
{
    public static final EliteBonusInterdictors1 INSTANCE = new EliteBonusInterdictors1();

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

    @Override
    public String toString() {
        return "EliteBonusInterdictors1";
    }
}
