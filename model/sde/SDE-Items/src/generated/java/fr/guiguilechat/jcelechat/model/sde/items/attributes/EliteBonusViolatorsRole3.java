package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * eliteBonusViolatorsRole3
 */
public class EliteBonusViolatorsRole3
    extends IntAttribute
{
    public final static EliteBonusViolatorsRole3 INSTANCE = new EliteBonusViolatorsRole3();

    @Override
    public int getId() {
        return  1279;
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
        return "EliteBonusViolatorsRole3";
    }
}