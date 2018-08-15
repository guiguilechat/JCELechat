package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Max research gang size bonus
 */
public class ResearchGangSizeBonus
    extends IntAttribute
{
    public final static ResearchGangSizeBonus INSTANCE = new ResearchGangSizeBonus();

    @Override
    public int getId() {
        return  407;
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
        return "ResearchGangSizeBonus";
    }
}