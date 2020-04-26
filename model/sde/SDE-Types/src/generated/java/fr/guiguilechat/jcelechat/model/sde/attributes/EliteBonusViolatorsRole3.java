package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusViolatorsRole3
 */
public class EliteBonusViolatorsRole3
    extends IntAttribute
{
    public static final EliteBonusViolatorsRole3 INSTANCE = new EliteBonusViolatorsRole3();

    @Override
    public int getId() {
        return  1279;
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
        return "EliteBonusViolatorsRole3";
    }
}
