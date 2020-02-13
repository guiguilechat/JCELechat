package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * eliteBonusViolatorsRole2
 */
public class EliteBonusViolatorsRole2
    extends IntAttribute
{
    public static final EliteBonusViolatorsRole2 INSTANCE = new EliteBonusViolatorsRole2();

    @Override
    public int getId() {
        return  1269;
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
        return "EliteBonusViolatorsRole2";
    }
}
